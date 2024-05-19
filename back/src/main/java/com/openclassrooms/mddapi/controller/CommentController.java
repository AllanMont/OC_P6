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


/**
 * Controller class for handling comment-related HTTP requests.
 */
@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;

    /**
     * Constructor for CommentController.
     * @param commentService The CommentService instance to be injected.
     * @param userService The UserService instance to be injected.
     */
    public CommentController(CommentService commentService, UserService userService) {
        this.commentService = commentService;
        this.userService = userService;
    }

    /**
     * Endpoint to retrieve all comments by post ID.
     * @param id The ID of the post to retrieve comments for.
     * @return List of comments for the specified post.
     */
    @GetMapping("/post/{id}")
    public List<Comment> getAllCommentsByPostId(@PathVariable Integer id) {
        return commentService.getAllCommentsByPostId(id);
    }
    
    /**
     * Endpoint to create a new comment.
     * @param comment The comment DTO containing the details of the new comment.
     * @param authentication The Authentication object representing the current authenticated user.
     * @return ResponseEntity with HTTP status 201 (CREATED) if successful, or 500 (INTERNAL SERVER ERROR) if an error occurs.
     */
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
    
    /**
     * Endpoint to delete a comment by ID.
     * @param id The ID of the comment to delete.
     * @return ResponseEntity with HTTP status 200 (OK) if successful, or 404 (NOT FOUND) if the comment does not exist.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Integer id) {
        if(commentService.getCommentById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        commentService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
