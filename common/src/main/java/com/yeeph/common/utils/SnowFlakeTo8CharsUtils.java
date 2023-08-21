package com.yeeph.common.utils;

import java.util.HashMap;

public class SnowFlakeTo8CharsUtils {

    // A-Z(65-90,0-25) a-z(97-122,26-51) 0-9(48-58,52-61) -(45,62) _(95,63)
    private static final String shuffleStr= "p8IwHLsRWbcMnruKQNXf-qPy21oEd5gBOik6_7YAZvTF4GDV3ezamlJxh9jtCUS0";

    public static String long2String(Long id){
        if(id==null || id < 0)
            return null;
        String idStr = Long.toBinaryString(id);
        StringBuilder prefix = new StringBuilder();
        for(int i=0;i<66-idStr.length();i++)
            prefix.append("0");
        idStr += prefix.toString();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<idStr.length();i+=6){
            String charStr = idStr.substring(i,i+6);
            int number = Integer.valueOf(charStr,2);
            sb.append(shuffleStr.charAt(number));
        }
        return sb.toString();
    }

}
