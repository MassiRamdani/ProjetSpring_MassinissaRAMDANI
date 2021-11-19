package com.m2dfs.ApiRestMeteo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Api
@RestController
public class MeteoAlertController {

    private Integer codeville;
    private String lan = "fr-fr";
    private String apiKey = "rqsBweGZwJVq21gLp1lFQVFrRonrHMyX";

    @Autowired
    RestTemplate restTemplate;


    public Integer getCodeVille(String ville) {
        codeville = -1999999;
        String response = restTemplate.exchange("http://dataservice.accuweather.com/locations/v1/cities/search?" +
                        "apikey=" + apiKey
                        + "&q=" + ville + "&language=" + lan + "&details=false",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
                }, ville).getBody();
        try {
            JSONArray jsonArray = new JSONArray(response);
            JSONObject jsonResponse;
            jsonResponse = jsonArray.getJSONObject(0);
            codeville = jsonResponse.getInt("Key");
            System.out.println("code test : " + codeville);
        } catch (JSONException err) {
            System.out.println("Error  get json object");
        }
        return codeville;
    }

    @ApiOperation(value = "Get alert weather by one day", response = Iterable.class, tags = "getOneDayAlert")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success | OK"),
            @ApiResponse(code = 401, message = "error | Unauthorized"),
            @ApiResponse(code = 403, message = "error | Forbidden"),
            @ApiResponse(code = 404, message = "error | Not found"),
            @ApiResponse(code = 500, message = "error | Internal server ")
    })
    @RequestMapping(value = "getOneDayAlert/{nomVille}", method = RequestMethod.GET)
    public String get1DayWeatherAlerts(@PathVariable String nomVille) {
        int code = this.getCodeVille(nomVille);
        String response = restTemplate.exchange("http://dataservice.accuweather.com/alarms/v1/1day/" + code
                        + "?apikey=" + apiKey,
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
                }, code).getBody();
        return response;
    }

    @ApiOperation(value = "Get alert weather by five day", response = Iterable.class, tags = "getFiveDayAlert")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success | OK"),
            @ApiResponse(code = 401, message = "error | Unauthorized"),
            @ApiResponse(code = 403, message = "error | Forbidden"),
            @ApiResponse(code = 404, message = "error | Not found"),
            @ApiResponse(code = 500, message = "error | Internal server ")
    })
    @RequestMapping(value = "getFiveDayAlert/{nomVille}", method = RequestMethod.GET)
    public String get5DayWeatherAlerts(@PathVariable String nomVille) {
        int code = this.getCodeVille(nomVille);
        String response = restTemplate.exchange("http://dataservice.accuweather.com/alarms/v1/1day/" + code
                        + "?apikey=" + apiKey,
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
                }, code).getBody();
        return response;
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
