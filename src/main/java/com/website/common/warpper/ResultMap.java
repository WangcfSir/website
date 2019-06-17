/*
 * Copyright (c) 2001-2017 GuaHao.com Corporation Limited. All rights reserved. 
 * This software is the confidential and proprietary information of GuaHao Company. 
 * ("Confidential Information"). 
 * You shall not disclose such Confidential Information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.website.common.warpper;

/**
 *
 * @author yanqb
 * @version V1.0
 * @since 2018-11-28 08:54
 */
public class ResultMap<T> {

    /**
     * 返回状态码 正常:0;
     */
    private Integer code;

    /**
     * 返回的message
     */
    private String msg;

    /**
     * 返回的message
     */
    private Integer count;

    /**
     * 返回的JSON数据对象
     */
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResultMap() {
    }

    public ResultMap(Integer code, String msg, Integer count, T data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }
}
