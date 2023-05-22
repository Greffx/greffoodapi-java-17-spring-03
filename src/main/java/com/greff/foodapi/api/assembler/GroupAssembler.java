package com.greff.foodapi.api.assembler;

import com.greff.foodapi.api.model.response.GroupResponse;
import com.greff.foodapi.domain.mapper.GroupMapper;
import com.greff.foodapi.domain.model.Group;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@Component
public class GroupAssembler {

    private final GroupMapper groupMapper;

    public GroupResponse toModel(Group group) {
        return groupMapper.fromGroupToGroupResponse(group);
    }

    //using Collection in here cuz group is Set in some entity classes and list to other classes, Collection is all that and more
    //since Set,List is inherited collection, is ok to use, like a general term for them
    public List<GroupResponse> toCollectionModel(Collection<Group> groups) {
        return groups.stream().map(this::toModel).toList();
    }
}
