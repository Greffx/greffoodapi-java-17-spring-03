package com.greff.foodapi.api.controller;

import com.greff.foodapi.api.assembler.PermissionAssembler;
import com.greff.foodapi.api.model.response.PermissionResponse;
import com.greff.foodapi.domain.usecase.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/groups/{groupId}/permissions")
public class GroupPermissionController {

    private final PermissionAssembler permissionAssembler;
    private final GroupService groupService;

    @GetMapping
    public Set<PermissionResponse> findAllPermissions(@PathVariable Long groupId) {
        var group = groupService.findById(groupId);

        var permissions = group.getPermission();

        return permissionAssembler.toCollectionModel(permissions);
    }

    @PutMapping("/{permissionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void permissionAsassociation(@PathVariable Long groupId, @PathVariable Long permissionId) {
        groupService.associatePermission(groupId, permissionId);
    }

    @DeleteMapping("/{permissionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void permissionDisassociation(@PathVariable Long groupId, @PathVariable Long permissionId) {
        groupService.disassociatePermission(groupId, permissionId);
    }
}
