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

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/db")
    public String db(){
        return "dbDistributor";
    }

    @GetMapping("/sql")
    public String sql(){
        return "sqlDistributor";
    }

    @GetMapping("/oracleSql")
    public String oracleSql(){
        return "oracleSqlDistributor";
    }

    @GetMapping("/oracleInsertRepeatable")
    public String oracleInsertRepeatable(){
        return "oracleInsertRepeatable";
    }
}
