package com.ws.utils;

import com.ws.enums.CodeEnum;

/**
 * @author wangsaisoon
 * @title
 * @time 2018/3/29 0029 下午 5:02
 */
public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(String code, Class<T> enumClass) {
        for (T each : enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
