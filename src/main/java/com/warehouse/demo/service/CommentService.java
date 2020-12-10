package com.warehouse.demo.service;

import com.warehouse.demo.repository.CommentRepository;
import org.springframework.stereotype.Service;


@Service
public class CommentService {

    private CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

}
