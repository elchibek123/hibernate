package java15;

import java15.config.HibernateConfig;
import java15.entities.BlogPost;
import java15.entities.Comment;
import java15.services.BlogPostService;
import java15.services.CommentService;
import java15.services.impl.BlogPostServiceImpl;
import java15.services.impl.CommentServiceImpl;

public class App {
    public static void main( String[] args ) {
//        HibernateConfig.getEntityManagerFactory();

////////// Blog Posts

        BlogPostService blogPostService = new BlogPostServiceImpl();

        BlogPost blogPost = BlogPost.builder().title("Java Hibernate").content("Some content about java hibernate").build();
        BlogPost newBlogPost = BlogPost.builder().title("Python").content("Some content about python").build();

//        System.out.println(blogPostService.createBlogPost(blogPost));
//        System.out.println(blogPostService.createBlogPost(newBlogPost));

//        blogPostService.getAllBlogPosts().forEach(System.out::println);

//        System.out.println(blogPostService.getBlogPostById(1L));

//        System.out.println(blogPostService.updateBlogPost(1L, newBlogPost));

//        System.out.println(blogPostService.deleteBlogPost(1L));

//        blogPostService.searchBlogPostsByKeyword("python").forEach(System.out::println);

////////// Comments

        CommentService commentService = new CommentServiceImpl();

        Comment comment = Comment.builder().commentText("This is the first comment").build();
        Comment comment2 = Comment.builder().commentText("This is the second comment").build();
        Comment comment3 = Comment.builder().commentText("This is the third comment").build();

//        System.out.println(commentService.createComment(1L, comment));
//        System.out.println(commentService.createComment(1L, comment2));

//        commentService.getAllComments().forEach(System.out::println);

//        System.out.println(commentService.getCommentById(1L));

//        System.out.println(commentService.updateComment(1L, comment3));

//        System.out.println(commentService.deleteComment(2L));

//        commentService.sortPostByPublishDate().forEach(System.out::println);

//        commentService.groupCommentsByPost().forEach(System.out::println);
    }
}
