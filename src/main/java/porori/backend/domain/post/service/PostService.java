package porori.backend.domain.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import porori.backend.domain.club.model.entity.Club;
import porori.backend.domain.club.repository.ClubRepository;
import porori.backend.domain.member.model.dto.MemberResponseDTO;
import porori.backend.domain.member.repository.MemberRepository;
import porori.backend.domain.post.exception.PostException;
import porori.backend.domain.post.model.dto.PostCreateRequestDTO;
import porori.backend.domain.post.model.dto.PostResponseDTO;
import porori.backend.domain.post.model.dto.PostSubjectResponseDTO;
import porori.backend.domain.post.model.entity.Post;
import porori.backend.domain.post.model.entity.Subject;
import porori.backend.domain.post.repository.PostRepository;
import porori.backend.domain.user.service.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static porori.backend.domain.post.model.entity.Subject.EMPTY;
import static porori.backend.domain.post.model.entity.Subject.valueOfSubject;
import static porori.backend.global.common.status.BaseStatus.ACTIVE;
import static porori.backend.global.common.status.ErrorStatus.*;

@Service
@RequiredArgsConstructor
public class PostService {
    private final UserService userService;

    private final PostRepository postRepository;
    private final ClubRepository clubRepository;
    private final MemberRepository memberRepository;

    public PostResponseDTO createPost(String token, PostCreateRequestDTO postCreateRequestDTO) {
        Long userId = userService.getUserId(token);
        Long clubId = postCreateRequestDTO.getClubId();
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new PostException(INVALID_CLUB));
        verifyClubMember(club, userId);

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
        return PostResponseDTO.of(post, memberResponseDTO);
    }

    private void verifyClubMember(Club club, Long userId) {
        if (!memberRepository.existsByClubAndUserId(club, userId))
            throw new PostException(NOT_EXIST_MEMBER);
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
        verifyClubMember(club, userId);

        List<Post> posts = postRepository.findByClubAndStatus(club, ACTIVE);

        return posts.stream()
                .map(post -> {
                    MemberResponseDTO memberResponseDTO = userService.getMemberResponseDTO(List.of(post.getUserId())).get(0);
                    return PostResponseDTO.of(post, memberResponseDTO);
                })
                .collect(Collectors.toList());
    }

    public List<PostResponseDTO> getSubjectPosts(String token, Long clubId, String subject) {
        Long userId = userService.getUserId(token);
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new PostException(INVALID_CLUB));
        verifyClubMember(club, userId);
        verifySubject(subject);

        List<Post> posts = postRepository.findByClubAndSubjectAndStatus(club, valueOfSubject(subject), ACTIVE);

        return posts.stream()
                .map(post -> {
                    MemberResponseDTO memberResponseDTO = userService.getMemberResponseDTO(List.of(post.getUserId())).get(0);
                    return PostResponseDTO.of(post, memberResponseDTO);
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
        verifyClubMember(club, userId);
        verifyClubPost(postId, club);

        Post post = postRepository.findById(postId).orElseThrow(() -> new PostException(INVALID_POST));
        MemberResponseDTO memberResponseDTO = userService.getMemberResponseDTO(List.of(post.getUserId())).get(0);

        return PostResponseDTO.of(post, memberResponseDTO);
    }

    private void verifyClubPost(Long postId, Club club) {
        if (!postRepository.existsByPostIdAndClub(postId, club))
            throw new PostException(NOT_CLUB_POST);
    }
}
