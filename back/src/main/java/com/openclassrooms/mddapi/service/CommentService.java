package com.openclassrooms.mddapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.repository.CommentRepository;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
    
    public Comment getCommentById(Integer id) {
        return commentRepository.findById(id).orElse(null);
    }
    
    public List<Comment> getAllCommentsByPostId(Integer postId) {
        return commentRepository.findByPostId(postId);
    }

    public void create(Comment comment) {
        commentRepository.save(comment);
    }

    public void delete(Integer id) {
        commentRepository.deleteById(id);
    }

}
