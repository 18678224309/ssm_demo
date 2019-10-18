package com.jtfu.eunm;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SexEnum implements IEnum<Integer> {

    MAN(1,"男"),WOMAN(0,"女");

    private int value;

    private String label;

    SexEnum(final int value, final String label) {
        this.value = value;
        this.label = label;
    }


    public static SexEnum getLabelByValue(int value){
        SexEnum[] s= SexEnum.values();
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
