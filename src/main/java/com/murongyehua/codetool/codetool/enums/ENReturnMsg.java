package com.murongyehua.codetool.codetool.enums;

import lombok.Getter;

/**
 * @author liul
 * @version 1.0 2019/8/30
 */
@Getter
public enum ENReturnMsg {

    /**
     * 非法insert
     */
    ILLEGAL_INERT_SQL("非法的insert语句","1");

    private String label;
    private String value;

    ENReturnMsg(String label, String value) {
        this.label = label;
        this.value = value;
    }


}
