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
import porori.backend.global.common.status.BaseStatus;
import porori.backend.global.common.status.ErrorStatus;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static porori.backend.domain.post.model.entity.Subject.valueOfSubject;

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
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new PostException(ErrorStatus.INVALID_CLUB));
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
            throw new PostException(ErrorStatus.NOT_EXIST_MEMBER);
    }

    public List<PostSubjectResponseDTO> getPostSubjects() {
        return Arrays.stream(Subject.values())
                .filter(subject -> !(subject.getSubject().equals("")))
                .map(PostSubjectResponseDTO::from)
                .collect(Collectors.toList());
    }

    public List<PostResponseDTO> getAllPosts(String token, Long clubId) {
        Long userId = userService.getUserId(token);
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new PostException(ErrorStatus.INVALID_CLUB));
        verifyClubMember(club, userId);

        List<Post> posts = postRepository.findByClubAndStatus(club, BaseStatus.ACTIVE);

        return posts.stream()
                .map(post -> {
                    MemberResponseDTO memberResponseDTO = userService.getMemberResponseDTO(List.of(post.getUserId())).get(0);
                    return PostResponseDTO.of(post, memberResponseDTO);
                })
                .collect(Collectors.toList());
    }
}
