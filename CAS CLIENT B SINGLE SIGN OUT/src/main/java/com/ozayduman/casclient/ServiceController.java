package com.ozayduman.casclient;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ozayd on 11.08.2017.
 */
@RestController
@RequestMapping("/report")
public class ServiceController {

    @RequestMapping("/hi")
    //@PreAuthorize("hasRole('ROLE_USER')")
    public String greeting() {
        return "service b 1";
    }

    @RequestMapping("/hi2")
    //@PreAuthorize("hasRole('ROLE_USER')")
    public String greeting2() {
        return "service b 2";
    }
}
