package ch.bdt.spike.spring.cloud.sessionapplication.web;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.TreeMap;

@RestController
@RequestMapping("/session")
@Slf4j
public class SessionController {

    @GetMapping("/log")
    public Object logSessionAttributes(HttpSession session) {
        session.getAttributeNames()
               .asIterator()
               .forEachRemaining(name -> log.info("{}: {}", name, session.getAttribute(name)));
        return "session attributes logged";
    }

    @GetMapping("/store")
    public Object setSessionAttributes(HttpSession session, String key, String value) {
        session.setAttribute(key, value);
        return "session attributes addded";
    }

    @GetMapping("/attrs")
    public Map<String, Object> getSessionAttributes(HttpSession session) {
        Map<String, Object> vAttrs = new TreeMap<>();
        session.getAttributeNames()
               .asIterator()
               .forEachRemaining(name -> vAttrs.put(name, session.getAttribute(name)));
        return vAttrs;
    }
}