package java15.services.impl;

import java15.dao.CommentDao;
import java15.dao.impl.CommentDaoImpl;
import java15.entities.Comment;
import java15.services.CommentService;

import java.util.List;
import java.util.Optional;

public class CommentServiceImpl implements CommentService {
    CommentDao commentDao = new CommentDaoImpl();

    @Override
    public String createComment(Long blogPostId, Comment comment) {
        return commentDao.save(blogPostId, comment) ? "Comment successfully saved" : "Failed to save comment";
    }

    @Override
    public List<Comment> getAllComments() {
        return commentDao.findAll();
    }

    @Override
    public Optional<Comment> getCommentById(Long id) {
        return commentDao.findById(id);
    }

    @Override
    public String updateComment(Long id, Comment newComment) {
        return commentDao.update(id, newComment) ? "Comment successfully updated" : "Failed to update comment";
    }

    @Override
    public String deleteComment(Long id) {
        return commentDao.delete(id) ? "Comment successfully deleted" : "Failed to delete comment";
    }

    @Override
    public List<Comment> sortPostByPublishDate() {
        return commentDao.sortPostByPublishDate();
    }

    @Override
    public List<Comment> groupCommentsByPost() {
        return commentDao.groupCommentsByPost();
    }
}
