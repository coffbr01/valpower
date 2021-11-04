package me.bcoffield.valpower.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.simple.SimpleHttpResponseFactory;
import jakarta.inject.Inject;
import me.bcoffield.valpower.dto.GetStatusDto;
import me.bcoffield.valpower.service.EC2Service;

@Controller("/api/ec2")
public class EC2Controller {
    @Inject
    private EC2Service ec2Service;

    @Post("/start")
    public HttpResponse<GetStatusDto> start() {
        return new SimpleHttpResponseFactory().ok(ec2Service.start());
    }

    @Post("/stop")
    public HttpResponse<GetStatusDto> stop() {
        return new SimpleHttpResponseFactory().ok(ec2Service.stop());
    }
}
