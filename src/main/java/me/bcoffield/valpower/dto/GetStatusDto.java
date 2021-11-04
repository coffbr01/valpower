package me.bcoffield.valpower.dto;

import lombok.Builder;
import lombok.Getter;
import software.amazon.awssdk.services.ec2.model.InstanceStateName;

@Builder
@Getter
public class GetStatusDto {
    private InstanceStateName status;
}
