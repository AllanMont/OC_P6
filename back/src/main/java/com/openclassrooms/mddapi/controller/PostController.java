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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    private final UserService userService;
    private final TopicService topicService;

    public PostController(PostService postService, UserService userService, TopicService topicService) {
        this.postService = postService;
        this.userService = userService;
        this.topicService = topicService;
    }

    @GetMapping("/topic/{id}")
    public List<Post> getAllPostsByTopicId(@PathVariable Integer id) {
        return postService.getPostsByTopicId(id);
    }

    @GetMapping("/topics")
    public List<Post> getAllPostsByTopicsId(@RequestBody List<Integer> topicsId) {
        return postService.getPostsByTopicsId(topicsId);
    }
    
    @GetMapping("/all")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }
    
    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Integer id) {
        return postService.getPostById(id);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody PostDto post) {
        if(userService.getUserById(post.getAuthorId()) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else if(topicService.getTopicById(post.getTopicId()) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Post newPost = new Post();
        newPost.setTitle(post.getTitle());
        newPost.setContent(post.getContent());
        newPost.setTopicId(post.getTopicId());
        newPost.setAuthorId(post.getAuthorId());
        newPost.setCreatedAt(LocalDateTime.now());

        postService.create(newPost);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Integer id) {
        if(postService.getPostById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        postService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
