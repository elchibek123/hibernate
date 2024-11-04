package java15.services.impl;

import java15.dao.BlogPostDao;
import java15.dao.impl.BlogPostDaoImpl;
import java15.entities.BlogPost;
import java15.services.BlogPostService;

import java.util.List;
import java.util.Optional;

public class BlogPostServiceImpl implements BlogPostService {
    BlogPostDao blogPostDao = new BlogPostDaoImpl();

    @Override
    public String createBlogPost(BlogPost blogPost) {
        return blogPostDao.save(blogPost) ? "Blog post successfully saved" : "Failed to save blog post";
    }

    @Override
    public List<BlogPost> getAllBlogPosts() {
        return blogPostDao.findAll();
    }

    @Override
    public Optional<BlogPost> getBlogPostById(Long id) {
        return blogPostDao.findById(id);
    }

    @Override
    public String updateBlogPost(Long id, BlogPost newBlogPost) {
        return blogPostDao.update(id, newBlogPost) ? "Blog post successfully updated" : "Failed to update blog post";
    }

    @Override
    public String deleteBlogPost(Long id) {
        return blogPostDao.delete(id) ? "Blog post successfully deleted" : "Failed to delete blog post";
    }

    @Override
    public List<BlogPost> searchBlogPostsByKeyword(String keyword) {
        return blogPostDao.searchBlogPostsByKeyword(keyword);
    }
}
