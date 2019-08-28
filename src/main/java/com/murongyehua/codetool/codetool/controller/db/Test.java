package com.murongyehua.codetool.codetool.controller.db;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author murongyehua
 * @version 1.0 2019/8/28
 */
@RestController
@RequestMapping("/test")
public class Test {

    @PostMapping("/test")
    public String test(){
        return "test success";
    }
}
