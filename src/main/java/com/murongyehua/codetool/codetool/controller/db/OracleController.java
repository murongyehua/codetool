package com.murongyehua.codetool.codetool.controller.db;

import com.murongyehua.codetool.codetool.dto.ResultMap;
import com.murongyehua.codetool.codetool.service.db.OracleSqlDealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liul
 * @version 1.0 2019/8/30
 */
@RestController
@RequestMapping("/db/oracle")
public class OracleController {

    @Autowired
    private OracleSqlDealService oracleSqlDealService;

    @PostMapping("/insertRepeatable")
    public ResultMap insertRepeatable(String sql, String indexField){
        return oracleSqlDealService.insertRepeatable(sql,indexField);
    }

}
