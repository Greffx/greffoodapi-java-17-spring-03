package com.greff.foodapi.api.controller;

import com.greff.foodapi.api.assembler.GroupAssembler;
import com.greff.foodapi.api.model.response.GroupResponse;
import com.greff.foodapi.domain.usecase.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/users/{userId}/groups")
public class UserGroupController {

    private final UserService userService;
    private final GroupAssembler groupAssembler;

    @GetMapping
    public List<GroupResponse> findAllGroups(@PathVariable Long userId) {
        var groups = userService.findById(userId).getGroups();

        return groupAssembler.toCollectionModel(groups);
    }

    @DeleteMapping("/{groupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void groupDisassociation(@PathVariable Long userId, @PathVariable Long groupId) {
        userService.groupDisassociation(userId, groupId);
    }

    @PutMapping("/{groupId}")
    public void groupAssociation(@PathVariable Long userId, @PathVariable Long groupId) {
        userService.groupAsassociation(userId, groupId);
    }
}
