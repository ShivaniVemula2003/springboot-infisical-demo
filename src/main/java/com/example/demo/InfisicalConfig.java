package com.example.demo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.infisical.sdk.InfisicalSdk;
import com.infisical.sdk.config.SdkConfig;

@Configuration
public class InfisicalConfig {

//    @Bean
//    public InfisicalClient infisicalClient() {
//        return new InfisicalClient(System.getenv("INFISICAL_TOKEN"));
//    }
	@Value("${test_token}")
    private String token;
	@Value("${infisical.site-url}")
    private String url;
	
    
    @Bean
    public InfisicalSdk infisicalSdk() throws Exception {
        InfisicalSdk sdk = new InfisicalSdk(
            new SdkConfig.Builder()
                .withSiteUrl(url) 
                .build()
        );
        sdk.Auth().SetAccessToken(token);
        return sdk;
    }
}

