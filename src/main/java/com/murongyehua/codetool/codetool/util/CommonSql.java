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
    String TABLE_NAME = "#tableName#";
    String INDEX_FIELD = "#indexField#";
    String INDEX_VALUE = "#indexValue#";
    String TABLE_COLUMNS = "#tableColumns#";
    String VALUE_AND_COLUMNS = "#valueAndColumns#";

    /**
     * 关键字
     */
    String INSERT = "insert";
    String INTO = "into";
    String FROM = "from";
    String VALUES = "values";
    /**
     * 模板
     */
    String ORACLE_INSERT_PREFIX = "insert when (not exists (select 1 form "+ TABLE_NAME +" where "+INDEX_FIELD +"= "+ INDEX_VALUE +")) then into "+ TABLE_NAME +
            "("+ TABLE_COLUMNS +") ";
    String ORACLE_INSERT_SUFFIX = "select "+ VALUE_AND_COLUMNS +" from dual;";
    String ORACLE_INSERT_TEMPLATE = ORACLE_INSERT_PREFIX + StrUtil.CRLF +ORACLE_INSERT_SUFFIX;

    /**
     * char
     */

    String LEFT_BRACKET = "(";
    String RIGHT_BRACKET = ")";
    String BRACKET = LEFT_BRACKET + RIGHT_BRACKET;
    String SEMICOLON = ";";

}
