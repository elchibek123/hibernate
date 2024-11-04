package java15.dao;

import java15.entities.BlogPost;

import java.util.List;
import java.util.Optional;

public interface BlogPostDao {
    boolean save(BlogPost blogPost);

    List<BlogPost> findAll();

    Optional<BlogPost> findById(Long id);

    boolean update(Long id, BlogPost newBlogPost);

    boolean delete(Long id);

    List<BlogPost> searchBlogPostsByKeyword(String keyword);
}
