package com.website.core.util;

public class StringUtil {

    // 把剩下的顺序，供管理员选择
    public static String getOptionalSort(String resultSort){
        if(resultSort == null){
            return "12345";
        }
        String optionalSort = "";
        for(int i=1;i<=5;i++){
            String current = String.valueOf(i);
            if(!resultSort.contains(current)) {
                optionalSort += current;
            }
        }
        return optionalSort;
    }
}
