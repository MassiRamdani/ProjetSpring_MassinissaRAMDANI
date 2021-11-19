package com.m2dfs.ApiRestMeteo.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Api
@RestController
public class MeteoMarineController {
    @Autowired
    RestTemplate restTemplate;
}
