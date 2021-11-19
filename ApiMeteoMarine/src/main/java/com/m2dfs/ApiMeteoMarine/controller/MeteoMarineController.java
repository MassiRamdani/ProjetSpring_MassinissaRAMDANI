
package com.m2dfs.ApiMeteoMarine.controller;


import com.m2dfs.ApiMeteoMarine.model.Meteo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Api(value = "MeteoMarineController", description = "REST Apis related to Meteo Entity!!!!")
@RestController
public class MeteoMarineController {


    List<Meteo> meteos = new ArrayList<>();

    {
        meteos.add(new Meteo(95, 9, 120, 40, 1020, "Ajaccio"));
        meteos.add(new Meteo(94, 8, 135, 39, 1035, "Basita"));
        meteos.add(new Meteo(93, 7, 128, 40, 1070, "Corte"));
        meteos.add(new Meteo(94, 8, 135, 39, 1035, "Bonifacio"));

    }

    @ApiOperation(value = "Get   weather for marine", response = Iterable.class, tags = "getMeteoMarine")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success | OK"),
            @ApiResponse(code = 401, message = "error | Unauthorized"),
            @ApiResponse(code = 403, message = "error | Forbidden"),
            @ApiResponse(code = 404, message = "error | Not found"),
            @ApiResponse(code = 500, message = "error | Internal server ")
    })
    @RequestMapping(value = "/getMeteoMarine/{plage}")
    public Meteo getMeteoMarine(@PathVariable(value = "plage") String plage) {
        return meteos.stream().filter(x -> x.getPlage().equalsIgnoreCase(plage)).collect(Collectors.toList()).get(0);
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}