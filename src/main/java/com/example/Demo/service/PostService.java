package com.example.Demo.service;

import com.example.Demo.dto.request.PostCreationRequest;
import com.example.Demo.entity.Post;
import com.example.Demo.repository.PostRepository;
import com.example.Demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class PostService {
    @Autowired
    private PostRepository postRepository;
    
    @Autowired
    private UserRepository userRepository;

    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    public Post createPost(PostCreationRequest request) {
        Post post = Post.builder()
                .title(request.getTitle())
                .body(request.getBody())
                .user(request.getUser())
                .status(request.getStatus())
                .build();
        return postRepository.save(post);
    }

}
