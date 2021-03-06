package com.shopping.apigateway;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @GetMapping("/")
    public String defaultMethod(){
        return "API gateway service is running";
    }

    @HystrixCommand
    @RequestMapping("/catalogServiceFallback")
    public String catalogServiceFallBackMethod(){
        return "Catalog service is taking long than expected."+" Please try again later";
    }

    @HystrixCommand
    @RequestMapping("/identityServiceFallback")
    public String identityServiceFallBackMethod(){
        return "Identity service is taking long than expected."+" Please try again later";
    }

    @HystrixCommand
    @RequestMapping("/customerServiceFallback")
    public String customerServiceFallBackMethod(){
        return "Customer service is taking long than expected."+" Please try again later";
    }
}
