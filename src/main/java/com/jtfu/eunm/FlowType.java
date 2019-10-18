package com.jtfu.eunm;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

public enum FlowType implements IEnum<Integer> {
    QJ(0,"请假"),ZZ(2,"转正")
    ,TX(3,"调薪"),LZ(4,"离职"),QT(1,"其他");
    private int value;

    private String label;

    FlowType(final int value, final String label) {
        this.value = value;
        this.label = label;
    }
    public static FlowType getLabelByValue(int value){
        FlowType[] s= FlowType.values();
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
