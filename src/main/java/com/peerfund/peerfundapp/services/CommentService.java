package com.peerfund.peerfundapp.services;

import com.peerfund.peerfundapp.dto.CommentDto;
import com.peerfund.peerfundapp.entities.Comment;

import java.util.List;
import java.util.Set;

public interface CommentService {

    void createComment(Long userId, Long groupId, CommentDto commentDto);

    List<Comment> allCommentsForAGroup();

}
