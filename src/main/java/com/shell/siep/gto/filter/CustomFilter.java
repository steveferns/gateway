package com.shell.siep.gto.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.Set;


@Component
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.Config> {
    public CustomFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        //Custom Pre Filter. Suppose we can extract JWT and perform Authentication
        return (exchange, chain) -> {
            HttpHeaders headers=exchange.getRequest().getHeaders();
            if (headers!=null){
                Set<Map.Entry<String,List<String>>> headeSet=headers.entrySet();
                headeSet.stream().forEach(k->System.out.println(k.getKey()+" - "+k.getValue()));
            }

            System.out.println("First pre filter" + exchange.getRequest().getPath());
            System.out.println("First pre filter" + exchange.getRequest().getQueryParams());
            //Custom Post Filter.Suppose we can call error response handler based on error code.
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                System.out.println("First post filter");
                if (exchange.getResponse().getHeaders().get("Access-Control-Allow-Origin")!=null) {
                    exchange.getResponse().getHeaders().get("Access-Control-Allow-Origin").stream().forEach(k->System.out.println(k));
                    System.out.println("Response header size " + exchange.getResponse().getHeaders().get("Access-Control-Allow-Origin").size());
                   // exchange.getResponse().getHeaders().remove(exchange.getResponse().getHeaders().get("Access-Control-Allow-Origin"));
                   // exchange.getResponse().getHeaders().add("Access-Control-Allow-Origin","http://localhost:3000");
                    //exchange.getResponse().getHeaders().get("Access-Control-Allow-Origin").remove("http://localhost:3000");
                }
            }));
        };
    }

    public static class Config {
        // Put the configuration properties
    }
}