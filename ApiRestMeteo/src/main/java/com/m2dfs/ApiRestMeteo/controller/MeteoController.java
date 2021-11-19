
package com.m2dfs.ApiRestMeteo.controller;


import com.fasterxml.jackson.databind.util.JSONPObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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
public class MeteoController {
    private Integer codeville;
    private String lan = "fr-fr";
    private String apiKey = "rqsBweGZwJVq21gLp1lFQVFrRonrHMyX";

    @Autowired
    RestTemplate restTemplate;


    @ApiOperation(value = "Get current weather by city code", response = Iterable.class, tags = "getCurrentMeteoByName")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success | OK"),
            @ApiResponse(code = 401, message = "error | Unauthorized"),
            @ApiResponse(code = 403, message = "error | Forbidden"),
            @ApiResponse(code = 404, message = "error | Not found"),
            @ApiResponse(code = 500, message = "error | Internal server error (probably exceeded request limit)")
    })
    @RequestMapping(value = "getCurrentMeteoByName/{nomVille}", method = RequestMethod.GET)
    public String getWeatherByCode(@PathVariable String nomVille) {
        int code = this.getCodeVille(nomVille);

        String response = restTemplate.exchange("http://dataservice.accuweather.com/currentconditions/v1/" + code
                        + "?apikey=" + apiKey + "&language=" + lan + "&details=false",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
                }, code).getBody();
        return response;
    }

    @ApiOperation(value = "Get 1 day of weather forecasts for city", response = Iterable.class, tags = "getOneDayForecasts")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success | OK"),
            @ApiResponse(code = 401, message = "error | Unauthorized"),
            @ApiResponse(code = 403, message = "error | Forbidden"),
            @ApiResponse(code = 404, message = "error | Not found"),
            @ApiResponse(code = 500, message = "error | Internal server error (probably exceeded request limit)")
    })
    @RequestMapping(value = "getOneDayForecasts/{nomVille}", method = RequestMethod.GET)
    public String get1DayDailyForecasts(@PathVariable String nomVille) {
        int code = this.getCodeVille(nomVille);
        String response = restTemplate.exchange("http://dataservice.accuweather.com/forecasts/v1/daily/1day/" + code +
                        "?apikey=" + apiKey + "&language=" + lan + "&details=false",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
                }, code).getBody();
        return response;

    }


    public Integer getCodeVille(String ville) {
        codeville = 0;
        String response = restTemplate.exchange("http://dataservice.accuweather.com/locations/v1/cities/search?apikey=" + apiKey
                        + "&q=" + ville + "&language=" + lan + "&details=false",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
                }, ville).getBody();
        try {
            JSONArray jsonArray = new JSONArray(response);
            JSONObject jsonResponse;
            jsonResponse = jsonArray.getJSONObject(0);
            codeville = jsonResponse.getInt("Key");
            System.out.println("code : " + codeville);
        } catch (JSONException err) {
            System.out.println("Erreur from get json object");
        }
        return codeville;
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}