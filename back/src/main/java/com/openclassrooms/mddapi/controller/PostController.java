package com.openclassrooms.mddapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.service.PostService;
import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.service.UserService;
import com.openclassrooms.mddapi.service.TopicService;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Controller class for handling post-related HTTP requests.
 */
@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final UserService userService;
    private final TopicService topicService;

    /**
     * Constructor for PostController.
     * @param postService The PostService instance to be injected.
     * @param userService The UserService instance to be injected.
     * @param topicService The TopicService instance to be injected.
     */
    public PostController(PostService postService, UserService userService, TopicService topicService) {
        this.postService = postService;
        this.userService = userService;
        this.topicService = topicService;
    }

    /**
     * Endpoint to retrieve all posts by topic ID.
     * @param id The ID of the topic.
     * @return List of posts for the specified topic.
     */
    @GetMapping("/topic/{id}")
    public List<Post> getAllPostsByTopicId(@PathVariable Integer id) {
        return postService.getPostsByTopicId(id);
    }

    /**
     * Endpoint to retrieve all posts by multiple topic IDs.
     * @param topicsId List of topic IDs.
     * @return List of posts for the specified topics.
     */
    @GetMapping("/topics")
    public List<Post> getAllPostsByTopicsId(@RequestBody List<Integer> topicsId) {
        return postService.getPostsByTopicsId(topicsId);
    }
    
    /**
     * Endpoint to retrieve all posts.
     * @return List of all posts.
     */
    @GetMapping("/all")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }
    
    /**
     * Endpoint to retrieve a post by ID.
     * @param id The ID of the post.
     * @return The post with the specified ID.
     */
    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Integer id) {
        return postService.getPostById(id);
    }

    /**
     * Endpoint to create a new post.
     * @param post The post data to be created.
     * @param authentication The authentication object containing user details.
     * @return ResponseEntity with HTTP status 201 (CREATED) if successful, or an appropriate error status if unsuccessful.
     */
    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody PostDto post, Authentication authentication) {
        Integer userId = userService.getUserIdByName(authentication);

        if(userService.getUserById(userId) == null || topicService.getTopicById(post.getTopicId()) == null || userId == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Post newPost = new Post();
        newPost.setTitle(post.getTitle());
        newPost.setContent(post.getContent());
        newPost.setTopicId(post.getTopicId());
        newPost.setAuthorId(userId);
        newPost.setCreatedAt(LocalDateTime.now());

        postService.create(newPost);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Endpoint to delete a post.
     * @param id The ID of the post to delete.
     * @return ResponseEntity with HTTP status 200 (OK) if successful, or an appropriate error status if unsuccessful.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Integer id) {
        if(postService.getPostById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        postService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
