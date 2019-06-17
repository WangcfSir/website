package com.website.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * 协会信息
 *
 * @author yanqb
 * @since 2018-12-10
 */
public class Association extends Model<Association> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;

    /**
     * 模块:协会信息(1协会简介;2协会功能;3联系我们)
     */
	private Integer type;

    /**
     * 标题
     */
	private String title;

    /**
     * 图片地址
     */
	private String picture;

    /**
     * 内容
     */
	private String content;

    /**
     * 发布时间
     */
	@TableField("create_time")
	private Date createTime;

    /**
     * 修改时间
     */
	@TableField("modify_time")
	private Date modifyTime;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Association{" +
			"id=" + id +
			", type=" + type +
			", title=" + title +
			", picture=" + picture +
			", content=" + content +
			", createTime=" + createTime +
			", modifyTime=" + modifyTime +
			"}";
	}
}
