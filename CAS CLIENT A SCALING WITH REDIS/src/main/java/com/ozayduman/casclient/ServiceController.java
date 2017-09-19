package com.ozayduman.casclient;

import com.ozayduman.casclient.repository.Citizen;
import com.ozayduman.casclient.repository.CitizenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by ozayd on 11.08.2017.
 */
@RestController
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    private CitizenRepository citizenRepository;

    @RequestMapping("/greeting")
    public String greeting() {
        return "service1";
    }

    @RequestMapping("/greeting2")
    public String greeting2() {
        return "service2";
    }

    @RequestMapping("/session")
    public String session(HttpSession session) {
        return "session id : " +session.getId();
    }

    @RequestMapping("/put")
    public String sessionPut(HttpSession session) {
        session.setAttribute(session.getId()+"p","özay");
        return "session id : " +session.getId();
    }

    @RequestMapping("/get")
    public String sessionGet(HttpSession session) {
        return session.getAttribute(session.getId()+"p").toString();
    }

    @RequestMapping("/saveCitizen")
    public String saveCitizen(HttpSession session) {
        final Citizen citizen = new Citizen(session.getId(),"Özay DUMAN", Citizen.Gender.MALE,1);
        citizenRepository.saveCitizen(citizen);
        return "citizenSaved";
    }

    @RequestMapping("/findCitizen")
    public Citizen findCitizen(HttpSession session) {
        Citizen citizen = citizenRepository.findCitizen(session.getId());
        return citizen;
    }

    @RequestMapping("/deleteCitizen")
    public String deleteCitizen(HttpSession session) {
        citizenRepository.deleteCitizen(session.getId());
        return "citizen deleted";
    }
}
