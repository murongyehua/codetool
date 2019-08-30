package com.murongyehua.codetool.codetool.util;

import cn.hutool.core.util.StrUtil;

/**
 * @author liul
 * @version 1.0 2019/8/30
 */
public interface CommonSql {

    /**
     * 模板参数
     */
    public static final String TABLE_NAME = "*tableName*";
    public static final String INDEX_FIELD = "*indexField*";
    public static final String INDEX_VALUE = "*indexValue*";
    public static final String TABLE_COLUMNS = "*tableColumns*";
    public static final String VALUE_AND_COLUMS = "*valueAndColumns*";

    /**
     * 关键字
     */
    public static final String INSERT = "insert";
    public static final String INTO = "into";
    public static final String FROM = "from";
    public static final String VALUES = "values";
    /**
     * 模板
     */
    public static final String ORACLE_INSERT_PREFIX = "insert when (not exists (select 1 form "+ TABLE_NAME +"where "+INDEX_FIELD +"= "+ INDEX_VALUE +")) then into "+ TABLE_NAME +
            "("+ TABLE_COLUMNS +") ";
    public static final String ORACLE_INSERT_SUFFIX = "select "+ VALUE_AND_COLUMS +" from dual";
    public static final String ORACLE_INSERT_TEMPLATE = ORACLE_INSERT_PREFIX + StrUtil.CRLF +ORACLE_INSERT_SUFFIX;


}
