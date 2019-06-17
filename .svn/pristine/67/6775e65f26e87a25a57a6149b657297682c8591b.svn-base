package com.website.system.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 医生类别表
 *
 * @author wangcf
 * @since 2019-06-03
 */
public class DoctorCategory {

    /**
     * 主键id
     */
    @TableId(value="id", type= IdType.AUTO)
    private Integer id;

    /**
     * 名称
     */
    @TableField("type")
    private String type=null;

    /**
     * 介绍
     */
    private String detail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "DoctorCategory{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", detai='" + detail + '\'' +
                '}';
    }
}
