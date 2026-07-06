package com.csc340.fitmatch;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

  @GetMapping({ "", "/", "/index" })
  public String homePage() {
    return "index";
  }

}
