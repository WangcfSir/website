package com.website.system.model.vo;

public enum ArticleTypeEnum {
    DYNAMIC_CENTER(1,"文件公告"),
    FILE_ANNOUNCEMENT(2,"行业动态"),
    POLICIES_REGULATIONS(3,"政策法规"),
    GREEN_BUILDING(4,"绿色建筑"),
    OTHER(0,"建设安全");

    private Integer code;
    private String name;

    ArticleTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(Integer code) {
        if(code==null){
            return "未知";
        }
        for (ArticleTypeEnum emu : ArticleTypeEnum.values()) {
            if (emu.code.equals(code)) {
                return emu.name;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
