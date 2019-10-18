package com.jtfu.eunm;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

public enum RoleEnum implements IEnum<Integer> {

    ADMIN(0,"管理员"),JL(2,"经理")
    ,ZJ(3,"总监"),ZG(4,"主管")
    ,ZY(5,"职员");

    private int value;

    private String label;

    RoleEnum(final int value, final String label) {
        this.value = value;
        this.label = label;
    }
    public static RoleEnum getLabelByValue(int value){
        RoleEnum[] s= RoleEnum.values();
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
