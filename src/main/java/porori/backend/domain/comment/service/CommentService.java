package porori.backend.domain.comment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import porori.backend.domain.club.model.entity.Club;
import porori.backend.domain.comment.exception.CommentException;
import porori.backend.domain.comment.model.dto.CommentCreateRequestDTO;
import porori.backend.domain.comment.model.dto.CommentDeleteResponseDTO;
import porori.backend.domain.comment.model.dto.CommentResponseDTO;
import porori.backend.domain.comment.model.entity.Comment;
import porori.backend.domain.comment.repository.CommentRepository;
import porori.backend.domain.member.model.dto.MemberResponseDTO;
import porori.backend.domain.member.repository.MemberRepository;
import porori.backend.domain.post.model.entity.Post;
import porori.backend.domain.post.repository.PostRepository;
import porori.backend.domain.user.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

import static porori.backend.global.common.status.BaseStatus.ACTIVE;
import static porori.backend.global.common.status.BaseStatus.INACTIVE;
import static porori.backend.global.common.status.ErrorStatus.*;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final UserService userService;

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public CommentResponseDTO createComment(String token, CommentCreateRequestDTO commentCreateRequestDTO) {
        Long userId = userService.getUserId(token);
        Long postId = commentCreateRequestDTO.getPostId();
        Post post = postRepository.findById(postId).orElseThrow(() -> new CommentException(INVALID_POST));
        Club club = post.getClub();
        verifyClubMember(club, userId);

        Comment comment = Comment.builder()
                .userId(userId)
                .post(post)
                .content(commentCreateRequestDTO.getContent())
                .build();

        commentRepository.save(comment);
        MemberResponseDTO memberResponseDTO = userService.getMemberResponseDTO(List.of(userId)).get(0);
        return CommentResponseDTO.of(comment, memberResponseDTO, true);
    }

    private void verifyClubMember(Club club, Long userId) {
        if (!memberRepository.existsByClubAndUserId(club, userId))
            throw new CommentException(NOT_EXIST_MEMBER);
    }

    public List<CommentResponseDTO> getAllComments(String token, Long postId) {
        Long userId = userService.getUserId(token);
        Post post = postRepository.findById(postId).orElseThrow(() -> new CommentException(INVALID_POST));
        Club club = post.getClub();
        verifyClubMember(club, userId);

        List<Comment> comments = commentRepository.findByPostAndStatus(post, ACTIVE);

        return comments.stream()
                .map(comment -> {
                    Long writerId = comment.getUserId();
                    MemberResponseDTO memberResponseDTO = userService.getMemberResponseDTO(List.of(writerId)).get(0);
                    boolean isWriter = writerId.equals(userId);
                    return CommentResponseDTO.of(comment, memberResponseDTO, isWriter);
                }).collect(Collectors.toList());
    }

    public CommentDeleteResponseDTO deleteComment(String token, Long commentId) {
        Long userId = userService.getUserId(token);
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new CommentException(INVALID_COMMENT));
        verifyUserComment(commentId, userId);

        comment.changeStatus(INACTIVE);
        commentRepository.save(comment);
        return CommentDeleteResponseDTO.from(comment);
    }

    private void verifyUserComment(Long commentId, Long userId) {
        if (!commentRepository.existsByCommentIdAndUserId(commentId, userId))
            throw new CommentException(NOT_USER_COMMENT);
    }
}
