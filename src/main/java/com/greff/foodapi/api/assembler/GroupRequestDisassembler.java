package com.greff.foodapi.api.assembler;

import com.greff.foodapi.api.model.request.GroupRequest;
import com.greff.foodapi.domain.mapper.GroupMapper;
import com.greff.foodapi.domain.model.Group;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class GroupRequestDisassembler {

    private final GroupMapper groupMapper;

    public Group toDomainObject(GroupRequest groupRequest) {
        return groupMapper.fromGroupRequestToGroup(groupRequest);
    }

    public void updateGroupDomainObject(GroupRequest groupRequest, Group group) {
        groupMapper.updateGroupDomainObject(groupRequest, group);
    }
}
