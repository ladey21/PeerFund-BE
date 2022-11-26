package com.peerfund.peerfundapp.services.implementation;

import com.peerfund.peerfundapp.dto.CommentDto;
import com.peerfund.peerfundapp.entities.Comment;
import com.peerfund.peerfundapp.entities.ContributionGroup;
import com.peerfund.peerfundapp.entities.User;
import com.peerfund.peerfundapp.exceptions.PeerFundException;
import com.peerfund.peerfundapp.repositories.CommentRepository;
import com.peerfund.peerfundapp.repositories.ContributionGroupRepository;
import com.peerfund.peerfundapp.repositories.UserRepository;
import com.peerfund.peerfundapp.services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Service
public class CommentServiceImpl implements CommentService {

    private final ContributionGroupRepository groupRepository;

    private final UserRepository userRepository;

    private final CommentRepository commentRepository;

    public CommentServiceImpl(ContributionGroupRepository groupRepository, UserRepository userRepository, CommentRepository commentRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public void createComment(Long userId, Long groupId, CommentDto commentDto) {
        User user = findUser(userId);
        ContributionGroup group = findGroup(groupId);
        boolean commentExist = group.getComments().stream().anyMatch(comment -> comment.getMember().getId().equals(userId));
        if(commentExist){
            throw new PeerFundException(HttpStatus.BAD_REQUEST,"comment exist already");
        } else{
            Comment comment = new Comment();
            comment.setMessage(commentDto.getMessage());
            comment.setMember(user);
            comment.setDate(LocalDateTime.now());
            comment.setContributionGroup(group);
            commentRepository.save(comment);
        }
    }

    @Override
    public List<Comment> allCommentsForAGroup() {
         List<Comment> comments = commentRepository.findAll();
         comments.sort(Comparator.comparing(comment -> comment.getDate().getMinute()));
         return comments;
    }

    private User findUser(Long id){
        return userRepository.findUserById(id);
    }

    private ContributionGroup findGroup(Long id){
        return groupRepository.findContributionGroupById(id);
    }
}
