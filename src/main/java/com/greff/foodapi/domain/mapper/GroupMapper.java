package com.greff.foodapi.domain.mapper;

import com.greff.foodapi.api.model.request.GroupRequest;
import com.greff.foodapi.api.model.response.GroupResponse;
import com.greff.foodapi.domain.model.Group;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GroupMapper {

    Group fromGroupRequestToGroup(GroupRequest groupRequest);

    GroupResponse fromGroupToGroupResponse(Group group);

    void updateGroupDomainObject(GroupRequest groupRequest, @MappingTarget Group group);
}
