package org.pcloud.cookieandsession.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class DemoApi {

    @GetMapping
    public String hello(HttpSession session, @RequestParam String id) {
        session.setAttribute("id", id);
        return "hello";
    }

    @GetMapping("logout")
    public String logOut(HttpSession session) {
        session.invalidate();
        return "logout";
    }

    @GetMapping("check")
    public String idCheck(HttpSession session) {
        Object id = session.getAttribute("id");

        return (String)id;
    }
}
