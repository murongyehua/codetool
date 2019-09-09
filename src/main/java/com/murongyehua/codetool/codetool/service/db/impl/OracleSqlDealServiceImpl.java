package com.murongyehua.codetool.codetool.service.db.impl;

import cn.hutool.core.util.StrUtil;
import com.murongyehua.codetool.codetool.dto.ResultMap;
import com.murongyehua.codetool.codetool.enums.ENReturnMsg;
import com.murongyehua.codetool.codetool.service.db.OracleSqlDealService;
import com.murongyehua.codetool.codetool.util.CommonSql;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author liul
 * @version 1.0 2019/8/30
 */
@Service
public class OracleSqlDealServiceImpl implements OracleSqlDealService {

    private static final int MIN_SQL_LENGTH = 6;

    private Stack<String> leftStack = new Stack<>();

    private Stack<String> rightStack = new Stack<>();

    private static final String CHAOS_STRING = "&&%%@@!--";

    @Override
    public ResultMap insertRepeatable(String sql, String indexField) {
        // 支持使用简单的oracle函数
        sql = sql.replace(CommonSql.BRACKET, CHAOS_STRING);
        String[] singleSqlArray = sql.replaceAll(StrUtil.CRLF, StrUtil.EMPTY).replaceAll(StrUtil.LF, StrUtil.SPACE).split(CommonSql.SEMICOLON);
        StringBuilder result = new StringBuilder();
        for (String string : singleSqlArray) {
            String singleResult = dealSingleSql(string.trim(),indexField);
            if (StrUtil.isEmpty(singleResult)) {
                continue;
            }
            result.append(singleResult).append(StrUtil.CRLF);
        }
        return ResultMap.isSuccess(result);
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
        if (singleSql.contains(CommonSql.SEMICOLON)) {
           singleSql = singleSql.replace(CommonSql.SEMICOLON, StrUtil.EMPTY);
        }
        String[] elements = singleSql.split(StrUtil.SPACE);
        List<String> elementList = new ArrayList<>();
        int tempIndex = -1;
        for (int i=0;i<elements.length;i++) {
            if (elements[i].contains(CommonSql.LEFT_BRACKET) && !elements[i].contains(CommonSql.RIGHT_BRACKET)) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int j=tempIndex + 1;j<elements.length;j++){
                    if (j >= i) {
                        stringBuilder.append(elements[j]);
                    }
                    if (elements[j].contains(CommonSql.RIGHT_BRACKET)) {
                        elementList.add(stringBuilder.toString());
                        tempIndex = j;
                        break;
                    }
                }
                continue;
            }
            if (i > tempIndex) {
                elementList.add(elements[i]);
            }
        }
        if (elementList.size() < MIN_SQL_LENGTH) {
            return null;
        }
        String template = null;
        String[] fields;
        if (elementList.get(3).startsWith(CommonSql.LEFT_BRACKET)) {
            fields = fieldDeal(elementList, indexField, true);
        }else if (elementList.get(2).contains(CommonSql.LEFT_BRACKET)) {
            fields = fieldDeal(elementList, indexField, false);
        }else {
            return null;
        }
        String[] values;
        int valuesIndex = elementList.indexOf(CommonSql.VALUES);
        if (elementList.get(valuesIndex).startsWith(CommonSql.LEFT_BRACKET)) {
            values = fieldValueDeal(elementList, indexField, fields[1], valuesIndex, Integer.parseInt(fields[2]),true);
        }else if (elementList.get(valuesIndex).contains(CommonSql.LEFT_BRACKET)){
            values = fieldValueDeal(elementList, indexField, fields[1], valuesIndex, Integer.parseInt(fields[2]), false);
        }else if (elementList.get(valuesIndex + 1).startsWith(CommonSql.LEFT_BRACKET)){
            values = fieldValueDeal(elementList, indexField, fields[1], valuesIndex+1, Integer.parseInt(fields[2]),true);
        }else {
            return null;
        }

        if (fields != null && values != null) {
            template = CommonSql.ORACLE_INSERT_TEMPLATE.replaceAll(CommonSql.TABLE_NAME, fields[0]).replaceAll(CommonSql.INDEX_FIELD,indexField).replaceAll(CommonSql.INDEX_VALUE,values[0])
                    .replaceAll(CommonSql.TABLE_COLUMNS,fields[1]).replaceAll(CommonSql.VALUE_AND_COLUMNS, values[1]).replace(CHAOS_STRING, CommonSql.BRACKET);
        }
        return template;
    }

    /**
     *
     * @param elementList
     * @param indexField
     * @param isStartWithBracket
     * @return 表名、字段组合、字段索引值，indexField不存在时=> null
     */
    private String[] fieldDeal(List<String> elementList, String indexField, boolean isStartWithBracket) {
        String[] strs = new String[3];
        if (isStartWithBracket) {
            if (elementList.get(3).contains(indexField)) {
                strs[0] = elementList.get(2);
                strs[1] = elementList.get(3).replace(CommonSql.LEFT_BRACKET, StrUtil.EMPTY).replace(CommonSql.RIGHT_BRACKET, StrUtil.EMPTY);
                strs[2] = Integer.toString(Arrays.asList(strs[1].split(StrUtil.COMMA)).indexOf(indexField));
            }
        }else {
            if (elementList.get(2).contains(indexField)) {
                String[] tempStr = elementList.get(2).split(CommonSql.LEFT_BRACKET);
                strs[0] = tempStr[0];
                strs[1] = tempStr[1].replace(CommonSql.RIGHT_BRACKET, StrUtil.EMPTY);
                strs[2] = Integer.toString(Arrays.asList(strs[1].split(StrUtil.COMMA)).indexOf(indexField));
            }
        }
        if (StrUtil.isNotEmpty(strs[0])) {
            return strs;
        }
        return null;
    }

    /**
     *
     * @param elementList
     * @param indexField
     * @param fields
     * @param indexFieldIndex
     * @param isStartWithBracket
     * @return 字段内容索引值、值-字段组合，值的个数不等于字段个数时 => null
     */
    private String[] fieldValueDeal(List<String> elementList, String indexField, String fields, int valuesIndex, int indexFieldIndex, boolean isStartWithBracket) {
        String[] strs = new String[2];
        List<String> values;
        if (isStartWithBracket) {
           values = Arrays.asList(elementList.get(valuesIndex).replace(CommonSql.LEFT_BRACKET, StrUtil.EMPTY).replace(CommonSql.RIGHT_BRACKET, StrUtil.EMPTY).split(StrUtil.COMMA));
        }else {
           values = Arrays.asList(elementList.get(valuesIndex).split(CommonSql.LEFT_BRACKET)[1].replace(CommonSql.RIGHT_BRACKET,StrUtil.EMPTY).split(StrUtil.COMMA));
        }
        String[] fieldNames = fields.split(StrUtil.COMMA);
        if (values.size() == fieldNames.length) {
            strs[0] = values.get(indexFieldIndex);
            StringBuilder stringBuilder = new StringBuilder();
            for (int i=0;i<values.size();i++){
                stringBuilder.append(values.get(i).trim()).append(StrUtil.SPACE).append(fieldNames[i]).append(StrUtil.COMMA);
            }
            strs[1] = stringBuilder.toString().substring(0,stringBuilder.length() - 1);
        }
        if (StrUtil.isNotEmpty(strs[0])) {
            return strs;
        }
        return null;
    }

}
