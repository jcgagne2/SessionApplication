package ch.bdt.spike.spring.cloud.sessionapplication.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloController {
    @Value("${spring.application.name}")
    private String applicationName;

    @RequestMapping("/")
    public String hello() {
        return "hello from " + applicationName;
    }

}
