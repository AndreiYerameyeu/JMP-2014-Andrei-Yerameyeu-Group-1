package com.epam.web.mvc;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "")
public class BaseController {
    
    protected final String allUrl = "/";
    protected final String homeUrl = "/home";
    protected final String indexUrl = "/index*";
    
    @RequestMapping(value = { homeUrl, allUrl, indexUrl }, method = { RequestMethod.POST, RequestMethod.GET })
    public String homePage(HttpServletRequest request, Locale loc) {
        request.getSession().getId();
        return "home";
    }
    
}
