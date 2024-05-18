package com.openclassrooms.mddapi.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.service.CommentService;
import com.openclassrooms.mddapi.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;
    private final UserService userService;

    public CommentController(CommentService commentService, UserService userService) {
        this.commentService = commentService;
        this.userService = userService;
    }

    @GetMapping("/post/{id}")
    public List<Comment> getAllCommentsByPostId(@PathVariable Integer id) {
        System.out.println("getAllCommentsByPostId");
        System.out.println(id);
        return commentService.getAllCommentsByPostId(id);
    }
    
    @PostMapping()
    public ResponseEntity<HttpStatus> create(@RequestBody CommentDto comment, Authentication authentication) {
        Integer userId = userService.getUserIdByName(authentication);

        Comment newComment = new Comment();
        newComment.setPostId(comment.getPostId());
        newComment.setAuthorId(userId);
        newComment.setContent(comment.getContent());
        newComment.setCreatedAt(LocalDateTime.now());
        
        commentService.create(newComment);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Integer id) {
        if(commentService.getCommentById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        commentService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
