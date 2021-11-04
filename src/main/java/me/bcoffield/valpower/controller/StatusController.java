package me.bcoffield.valpower.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.simple.SimpleHttpResponseFactory;
import jakarta.inject.Inject;
import me.bcoffield.valpower.dto.GetStatusDto;
import me.bcoffield.valpower.service.StatusService;

@Controller("/api/status")
public class StatusController {
    @Inject
    private StatusService statusService;

    @Get
    public HttpResponse<GetStatusDto> getStatus() {
        return new SimpleHttpResponseFactory().ok(statusService.getStatus());
    }
}
