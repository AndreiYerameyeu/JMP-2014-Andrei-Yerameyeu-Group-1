package com.epam.web;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "")
public class BaseController {
    
    protected final String allUrl = "/home";
    
    @RequestMapping(value = { allUrl }, method = { RequestMethod.POST, RequestMethod.GET })
    public String homePage(HttpServletRequest request, Locale loc) {
        return "home";
    }
    
}
