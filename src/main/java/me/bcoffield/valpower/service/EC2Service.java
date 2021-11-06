package me.bcoffield.valpower.service;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import me.bcoffield.valpower.ApplicationProperties;
import me.bcoffield.valpower.dto.GetStatusDto;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.InstanceStateName;
import software.amazon.awssdk.services.ec2.model.StartInstancesRequest;
import software.amazon.awssdk.services.ec2.model.StopInstancesRequest;

@Singleton
public class EC2Service {
    @Inject
    private ApplicationProperties applicationProperties;

    public GetStatusDto start() {
        Ec2Client ec2 = Ec2Client.builder().region(Region.of(applicationProperties.getRegion())).build();
        StartInstancesRequest request = StartInstancesRequest.builder().instanceIds(applicationProperties.getInstanceId()).build();
        ec2.startInstances(request);
        ec2.close();
        return GetStatusDto.builder().status(InstanceStateName.PENDING).build();
    }

    public GetStatusDto stop() {
        Ec2Client ec2 = Ec2Client.builder().region(Region.of(applicationProperties.getRegion())).build();
        StopInstancesRequest request = StopInstancesRequest.builder().instanceIds(applicationProperties.getInstanceId()).build();
        ec2.stopInstances(request);
        ec2.close();
        return GetStatusDto.builder().status(InstanceStateName.STOPPING).build();
    }
}
