package com.murongyehua.codetool.codetool.service.db;

import com.murongyehua.codetool.codetool.dto.ResultMap;

/**
 * @author murongyehua
 * @version 1.0 2019/8/30
 */
public interface OracleSqlDealService {

    ResultMap insertRepeatable(String sql, String indexField);
}
