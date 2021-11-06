package me.bcoffield.valpower.service;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import me.bcoffield.valpower.ApplicationProperties;
import me.bcoffield.valpower.dto.GetStatusDto;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.DescribeInstancesRequest;
import software.amazon.awssdk.services.ec2.model.DescribeInstancesResponse;
import software.amazon.awssdk.services.ec2.model.InstanceState;
import software.amazon.awssdk.services.ec2.model.InstanceStateName;

import java.util.concurrent.atomic.AtomicReference;

@Singleton
public class StatusService {
    @Inject
    private ApplicationProperties applicationProperties;

    public GetStatusDto getStatus() {
        Ec2Client ec2 = Ec2Client.builder().region(Region.of(applicationProperties.getRegion())).build();
        DescribeInstancesRequest request = DescribeInstancesRequest.builder().instanceIds(applicationProperties.getInstanceId()).build();
        DescribeInstancesResponse response = ec2.describeInstances(request);
        final AtomicReference<InstanceStateName> stateName = new AtomicReference<>();
        response.reservations().forEach(reservation -> {
            reservation.instances().forEach(instance -> {
                InstanceState state = instance.state();
                stateName.set(state.name());
            });
        });
        ec2.close();
        return GetStatusDto.builder().status(stateName.get()).build();
    }
}
