package com.murongyehua.codetool.codetool.controller.string;

import com.murongyehua.codetool.codetool.dto.ResultMap;
import com.murongyehua.codetool.codetool.service.string.StringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liul
 * @version 1.0 2019/9/3
 */
@RestController
@RequestMapping("/string")
public class StringController {

    @Autowired
    private StringService stringService;

    @PostMapping("/normalUUID")
    public ResultMap getNormalUUID(){
        return stringService.normalUUID();
    }

    @PostMapping("/simpleUUID")
    public ResultMap getSimpleUUID(){
        return stringService.simpleUUID();
    }

}
