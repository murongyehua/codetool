package com.murongyehua.codetool.codetool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author liul
 * @version 1.0 2019/9/5
 */
@Controller
public class WebController {

    @GetMapping("/testHtml")
    public String test(){
        return "Test";
    }
}
