package com.m2dfs.ApiMeteoMarine.util;


import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.Collections;


@Component
public class ApiMetooMarineInfoContributor implements InfoContributor {
  @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("details",
                Collections.singletonMap("description", "This is City Service, it exposes the data of a city"));
    }
}