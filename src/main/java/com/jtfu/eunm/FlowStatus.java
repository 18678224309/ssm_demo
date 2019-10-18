package com.jtfu.eunm;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

public enum FlowStatus implements IEnum<Integer>{

    SPZ(0,"审批中"),TG(2,"通过")
    ,JJ(3,"拒绝");

    private int value;

    private String label;

    FlowStatus(final int value, final String label) {
        this.value = value;
        this.label = label;
    }
    public static FlowStatus getLabelByValue(int value){
        FlowStatus[] s= FlowStatus.values();
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
