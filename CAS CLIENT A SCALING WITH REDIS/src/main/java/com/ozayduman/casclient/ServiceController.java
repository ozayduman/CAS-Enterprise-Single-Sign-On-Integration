package com.ozayduman.casclient;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Map;

/**
 * Created by ozayd on 11.08.2017.
 */
@RestController
@RequestMapping("/service")
public class ServiceController {

    @RequestMapping("/greeting")
    //@PreAuthorize("hasRole('ROLE_USER')")
    public String greeting() {
        return "service1";
    }

    @RequestMapping("/greeting2")
    //@PreAuthorize("hasRole('ROLE_USER')")
    public String greeting2() {
        return "service2";
    }

    @RequestMapping("/session")
    //@PreAuthorize("hasRole('ROLE_USER')")
    public String session(HttpSession session) {
        return "session id : " +session.getId();
    }
}
