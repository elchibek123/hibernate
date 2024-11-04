package java15.services;

import java15.entities.BlogPost;

import java.util.List;
import java.util.Optional;

public interface BlogPostService {
    String createBlogPost(BlogPost blogPost);

    List<BlogPost> getAllBlogPosts();

    Optional<BlogPost> getBlogPostById(Long id);

    String updateBlogPost(Long id, BlogPost newBlogPost);

    String deleteBlogPost(Long id);

    List<BlogPost> searchBlogPostsByKeyword(String keyword);
}
