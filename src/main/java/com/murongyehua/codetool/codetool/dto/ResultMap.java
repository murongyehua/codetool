package com.murongyehua.codetool.codetool.dto;

import com.murongyehua.codetool.codetool.enums.ENStatus;
import lombok.Data;

/**
 * @author murongyehua
 * @version 1.0 2019/8/30
 */
@Data
public class ResultMap {

    private String code;
    private String info;
    private Object data;
    private boolean success;

    public ResultMap(String code,String info,boolean success,Object data){
        this.code = code;
        this.info = info;
        this.success = success;
        this.data = data;
    }

    public ResultMap(){

    }

    public static ResultMap isSuccess(Object data){
        return new ResultMap(ENStatus.SUCCESS.getValue(),"操作成功",true,data);
    }

    public static ResultMap isFail(String info, Object data){
        return new ResultMap(ENStatus.FAIL.getValue(),info,false,data);
    }

}
