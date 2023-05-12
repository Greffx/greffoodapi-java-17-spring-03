package com.greff.foodapi.api.controller;

import com.greff.foodapi.api.assembler.GroupAssembler;
import com.greff.foodapi.api.assembler.GroupRequestDisassembler;
import com.greff.foodapi.api.model.request.GroupRequest;
import com.greff.foodapi.api.model.response.GroupResponse;
import com.greff.foodapi.domain.usecase.GroupService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;
    private final GroupAssembler groupAssembler;
    private final GroupRequestDisassembler groupRequestDisassembler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GroupResponse create(@RequestBody @Valid GroupRequest groupRequest) {
        var group = groupRequestDisassembler.toDomainObject(groupRequest);
        group = groupService.create(group);

        return groupAssembler.toModel(group);
    }

    @PutMapping("/{id}")
    public GroupResponse update(@RequestBody @Valid GroupRequest groupRequest, @PathVariable Long id) {
        var group = groupService.findById(id);

        groupRequestDisassembler.updateGroupDomainObject(groupRequest, group);
        groupService.update(group);

        return groupAssembler.toModel(group);
    }

    @GetMapping
    public List<GroupResponse> findAll() {
        var groups = groupService.findAll();

        return groupAssembler.toCollectionModel(groups);
    }

    @GetMapping("/{id}")
    public GroupResponse findById(@PathVariable Long id) {
        var group = groupService.findById(id);

        return groupAssembler.toModel(group);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        groupService.deleteById(id);
    }

}
