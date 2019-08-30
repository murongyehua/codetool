package com.murongyehua.codetool.codetool.enums;

import lombok.Getter;

/**
 * @author liul
 * @version 1.0 2019/8/30
 */
@Getter
public enum ENStatus {

    /**
     * 成功返回状态码
     */
    SUCCESS("成功","0"),
    /**
     * 失败返回状态码
     */
    FAIL("失败","1");

    private String label;
    private String value;

    ENStatus(String label, String value) {
        this.label = label;
        this.value = value;
    }


}
