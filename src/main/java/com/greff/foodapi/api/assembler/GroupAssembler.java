package com.greff.foodapi.api.assembler;

import com.greff.foodapi.api.model.response.GroupResponse;
import com.greff.foodapi.domain.mapper.GroupMapper;
import com.greff.foodapi.domain.model.Group;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class GroupAssembler {

    private final GroupMapper groupMapper;

    public GroupResponse toModel(Group group) {
        return groupMapper.fromGroupToGroupResponse(group);
    }

    public List<GroupResponse> toCollectionModel(List<Group> groups) {
        return groups.stream().map(this::toModel).toList();
    }
}
