package porori.backend.domain.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import porori.backend.domain.club.model.entity.Club;
import porori.backend.domain.club.repository.ClubRepository;
import porori.backend.domain.member.model.dto.MemberResponseDTO;
import porori.backend.domain.post.exception.PostException;
import porori.backend.domain.post.model.dto.PostCreateRequestDTO;
import porori.backend.domain.post.model.dto.PostDeleteResponseDTO;
import porori.backend.domain.post.model.dto.PostResponseDTO;
import porori.backend.domain.post.model.dto.PostSubjectResponseDTO;
import porori.backend.domain.post.model.entity.Post;
import porori.backend.domain.post.model.entity.Subject;
import porori.backend.domain.post.repository.PostRepository;
import porori.backend.domain.user.service.UserService;
import porori.backend.global.utils.Validator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static porori.backend.domain.post.model.entity.Subject.EMPTY;
import static porori.backend.domain.post.model.entity.Subject.valueOfSubject;
import static porori.backend.global.common.status.BaseStatus.ACTIVE;
import static porori.backend.global.common.status.BaseStatus.INACTIVE;
import static porori.backend.global.common.status.ErrorStatus.*;

@Service
@RequiredArgsConstructor
public class PostService {
    private final UserService userService;

    private final PostRepository postRepository;
    private final ClubRepository clubRepository;

    private final Validator validator;

    public PostResponseDTO createPost(String token, PostCreateRequestDTO postCreateRequestDTO) {
        Long userId = userService.getUserId(token);
        Long clubId = postCreateRequestDTO.getClubId();
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new PostException(INVALID_CLUB));
        validator.verifyClubMember(club, userId);

        Post post = Post.builder()
                .userId(userId)
                .club(club)
                .title(postCreateRequestDTO.getTitle())
                .content(postCreateRequestDTO.getContent())
                .isImportant(postCreateRequestDTO.isImportant())
                .subject(valueOfSubject(postCreateRequestDTO.getSubject()))
                .build();

        postRepository.save(post);
        MemberResponseDTO memberResponseDTO = userService.getMemberResponseDTO(List.of(userId)).get(0);
        return PostResponseDTO.of(post, memberResponseDTO, true);
    }

    public List<PostSubjectResponseDTO> getPostSubjects() {
        return Arrays.stream(Subject.values())
                .filter(subject -> !(subject.getSubject().equals("")))
                .map(PostSubjectResponseDTO::from)
                .collect(Collectors.toList());
    }

    public List<PostResponseDTO> getAllPosts(String token, Long clubId) {
        Long userId = userService.getUserId(token);
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new PostException(INVALID_CLUB));
        validator.verifyClubMember(club, userId);

        List<Post> posts = postRepository.findByClubAndStatus(club, ACTIVE);

        return posts.stream()
                .map(post -> {
                    Long writerId = post.getUserId();
                    MemberResponseDTO memberResponseDTO = userService.getMemberResponseDTO(List.of(writerId)).get(0);
                    boolean isWriter = writerId.equals(userId);
                    return PostResponseDTO.of(post, memberResponseDTO, isWriter);
                })
                .collect(Collectors.toList());
    }

    public List<PostResponseDTO> getSubjectPosts(String token, Long clubId, String subject) {
        Long userId = userService.getUserId(token);
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new PostException(INVALID_CLUB));
        validator.verifyClubMember(club, userId);
        verifySubject(subject);

        List<Post> posts = postRepository.findByClubAndSubjectAndStatus(club, valueOfSubject(subject), ACTIVE);

        return posts.stream()
                .map(post -> {
                    Long writerId = post.getUserId();
                    MemberResponseDTO memberResponseDTO = userService.getMemberResponseDTO(List.of(writerId)).get(0);
                    boolean isWriter = writerId.equals(userId);
                    return PostResponseDTO.of(post, memberResponseDTO, isWriter);
                })
                .collect(Collectors.toList());
    }

    private void verifySubject(String subject) {
        if (valueOfSubject(subject).equals(EMPTY))
            throw new PostException(INVALID_POST_SUBJECT);
    }

    public PostResponseDTO getPost(String token, Long clubId, Long postId) {
        Long userId = userService.getUserId(token);
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new PostException(INVALID_CLUB));
        validator.verifyClubMember(club, userId);
        verifyClubPost(postId, club);

        Post post = postRepository.findById(postId).orElseThrow(() -> new PostException(INVALID_POST));
        Long writerId = post.getUserId();
        MemberResponseDTO memberResponseDTO = userService.getMemberResponseDTO(List.of(writerId)).get(0);
        boolean isWriter = writerId.equals(userId);

        return PostResponseDTO.of(post, memberResponseDTO, isWriter);
    }

    private void verifyClubPost(Long postId, Club club) {
        if (!postRepository.existsByPostIdAndClub(postId, club))
            throw new PostException(NOT_CLUB_POST);
    }

    public PostDeleteResponseDTO deletePost(String token, Long postId) {
        Long userId = userService.getUserId(token);
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostException(INVALID_POST));
        verifyUserPost(postId, userId);

        post.changeStatus(INACTIVE);
        postRepository.save(post);
        return PostDeleteResponseDTO.from(post);
    }

    private void verifyUserPost(Long postId, Long userId) {
        if (!postRepository.existsByPostIdAndUserId(postId, userId))
            throw new PostException(NOT_USER_POST);
    }
}
