package com.epam.edp.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "")
public class BaseController {
  
  protected static final String ALL_URL = "/";
  protected static final String INDEX_URL = "/index*";
  protected static final String HOME_URL = "/home";
  
  @RequestMapping(value = { BaseController.HOME_URL, BaseController.ALL_URL, BaseController.INDEX_URL },
      method = { RequestMethod.POST, RequestMethod.GET })
  public String homePage(HttpServletRequest request) {
    request.getSession().getId();
    return "home";
  }
  
}
