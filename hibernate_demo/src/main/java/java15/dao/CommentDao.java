package java15.dao;

import java15.entities.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentDao {
    boolean save(Long blogPostId, Comment comment);

    List<Comment> findAll();

    Optional<Comment> findById(Long id);

    boolean update(Long id, Comment newComment);

    boolean delete(Long id);

    List<Comment> sortPostByPublishDate();

    List<Comment> groupCommentsByPost();
}
