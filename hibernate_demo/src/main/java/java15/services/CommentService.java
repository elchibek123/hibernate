package java15.services;

import java15.entities.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    String createComment(Long blogPostId, Comment comment);

    List<Comment> getAllComments();

    Optional<Comment> getCommentById(Long id);

    String updateComment(Long id, Comment newComment);

    String deleteComment(Long id);

    List<Comment> sortPostByPublishDate();

    List<Comment> groupCommentsByPost();
}
