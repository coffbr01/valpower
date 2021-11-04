package me.bcoffield.valpower.service;

import jakarta.inject.Singleton;
import me.bcoffield.valpower.Constants;
import me.bcoffield.valpower.dto.GetStatusDto;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.InstanceStateName;
import software.amazon.awssdk.services.ec2.model.StartInstancesRequest;
import software.amazon.awssdk.services.ec2.model.StopInstancesRequest;

@Singleton
public class EC2Service {
    public GetStatusDto start() {
        Ec2Client ec2 = Ec2Client.builder().region(Constants.REGION).build();
        StartInstancesRequest request = StartInstancesRequest.builder().instanceIds(Constants.INSTANCE_ID).build();
        ec2.startInstances(request);
        ec2.close();
        return GetStatusDto.builder().status(InstanceStateName.PENDING).build();
    }

    public GetStatusDto stop() {
        Ec2Client ec2 = Ec2Client.builder().region(Constants.REGION).build();
        StopInstancesRequest request = StopInstancesRequest.builder().instanceIds(Constants.INSTANCE_ID).build();
        ec2.stopInstances(request);
        ec2.close();
        return GetStatusDto.builder().status(InstanceStateName.STOPPING).build();
    }
}
