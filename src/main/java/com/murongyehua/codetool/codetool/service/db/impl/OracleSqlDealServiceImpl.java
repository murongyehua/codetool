package com.murongyehua.codetool.codetool.service.db.impl;

import com.murongyehua.codetool.codetool.dto.ResultMap;
import com.murongyehua.codetool.codetool.service.db.OracleSqlDealService;
import org.springframework.stereotype.Service;

/**
 * @author liul
 * @version 1.0 2019/8/30
 */
@Service
public class OracleSqlDealServiceImpl implements OracleSqlDealService {

    @Override
    public ResultMap insertRepeatable(String sql) {
        return ResultMap.isSuccess(sql);
    }

}
