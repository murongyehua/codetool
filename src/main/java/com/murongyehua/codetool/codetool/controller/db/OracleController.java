package com.murongyehua.codetool.codetool.controller.db;

import com.murongyehua.codetool.codetool.dto.ResultMap;
import com.murongyehua.codetool.codetool.service.db.OracleSqlDealService;
import org.springframework.beans.factory.annotation.Autowired;
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

    public ResultMap insertRepeatable(String sql){
        return oracleSqlDealService.insertRepeatable(sql);
    }

}
