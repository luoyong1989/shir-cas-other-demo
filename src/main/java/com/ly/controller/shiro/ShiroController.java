package com.ly.controller.shiro;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by ly on 2017/7/7.
 */
@Controller
@RequestMapping("/test")
public class ShiroController {
    @RequestMapping
    public String test(HttpServletRequest request){
        HttpSession session = request.getSession();
        return "html/test.html";
    }
    @RequestMapping("/403")
    public String test2(HttpServletRequest request){
        HttpSession session = request.getSession();
        return "html/403.html";
    }

    @RequestMapping("/")
    public String test3(HttpServletRequest request){
        HttpServletRequest request1 = request;
        return "html/index.html";
    }

}
