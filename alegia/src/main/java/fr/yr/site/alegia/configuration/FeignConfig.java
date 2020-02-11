package fr.yr.site.alegia.configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.auth.BasicAuthRequestInterceptor;
import org.apache.http.client.protocol.RequestAuthCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {


    @Bean
    public BasicAuthRequestInterceptor mBasicAuthRequestInterceptor(){
        return  new BasicAuthRequestInterceptor("user", "123");
    }
}
