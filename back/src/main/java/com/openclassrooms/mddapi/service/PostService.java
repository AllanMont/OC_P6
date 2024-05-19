package com.openclassrooms.mddapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.repository.PostRepository;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
    
    public Post getPostById(Integer id) {
        return postRepository.findById(id).orElse(null);
    }
    
    public List<Post> getPostsByTopicId(Integer topicId) {
        return postRepository.findByTopicId(topicId);
    }
    
    public List<Post> getPostsByTopicsId(List<Integer> topicsId) {
        List<Post> posts = new ArrayList<>();

        for (Integer topicId : topicsId) {
            posts.addAll(postRepository.findByTopicId(topicId));
        }

        return posts;
    }

    public void create(Post post) {
        postRepository.save(post);
    }

    public void delete(Integer id) {
        postRepository.deleteById(id);
    }
}
