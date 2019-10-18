package com.jtfu.eunm;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

public enum  StatusEnum implements IEnum<Integer> {
    SXQ(0,"实习期"),ZZ(2,"正式")
    ,XJ(3,"休假"),LZ(4,"离职");

    private int value;

    private String label;

    StatusEnum(final int value, final String label) {
        this.value = value;
        this.label = label;
    }
    public static StatusEnum getLabelByValue(int value){
        StatusEnum[] s= StatusEnum.values();
        for (int i = 0; i < s.length; i++) {
            if(value==s[i].getValue()){
                return s[i];
            }
        }
        return null;
    }

    public Integer getValue() {
        return this.value;
    }

    @JsonValue
    public String getLabel() {
        return label;
    }
}
