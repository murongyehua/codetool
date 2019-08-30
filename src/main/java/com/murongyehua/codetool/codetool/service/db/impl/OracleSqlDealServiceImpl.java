package com.murongyehua.codetool.codetool.service.db.impl;

import cn.hutool.core.util.StrUtil;
import com.murongyehua.codetool.codetool.dto.ResultMap;
import com.murongyehua.codetool.codetool.enums.ENReturnMsg;
import com.murongyehua.codetool.codetool.service.db.OracleSqlDealService;
import com.murongyehua.codetool.codetool.util.CommonSql;
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

    /**
     * @param singleSql 单条不换行的insert sql
     * @param indexField 索引字段,推荐使用主键
     * @return 转换后的sql/null，返回null时，代表sql非法
     */
    private String dealSingleSql(String singleSql, String indexField){
        if (StrUtil.isEmpty(singleSql)) {
            return null;
        }
        if (!singleSql.contains(CommonSql.INSERT)) {
           return null;
        }
        if (!singleSql.contains(CommonSql.INTO)) {
            return null;
        }
        if (!singleSql.contains(CommonSql.VALUES)){
            return null;
        }

        return null;
    }

}
