package com.website.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * 内容表（文件公告、行业动态、政策法规）
 * @author yanqb
 * @since 2018-11-18
 */
public class Article extends Model<Article> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;

    /**
     * 模块:1动态中心;2文件公告;3政策法规;4绿色建筑;0其他（建设安全）
     */
	private Integer type;

    /**
     * 状态:0已显示;1未显示;2暂存
     */
	private Integer state;

    /**
     * 创建人
     */
	@TableField("create_user")
	private Integer createUser;

    /**
     * 修改人
     */
	@TableField("modify_user")
	private Integer modifyUser;

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

    /**
     * 标题
     */
	private String title;

    /**
     * 项目内容
     */
	private String content;

    /**
     * 图片地址
     */
	private String picture;

    /**
     * 文件地址
     */
	private String file;

    /**
     * 备注
     */
	private String remark;


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

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	public Integer getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(Integer modifyUser) {
		this.modifyUser = modifyUser;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "article{" +
			"id=" + id +
			", type=" + type +
			", state=" + state +
			", createUser=" + createUser +
			", modifyUser=" + modifyUser +
			", createTime=" + createTime +
			", modifyTime=" + modifyTime +
			", title=" + title +
			", content=" + content +
			", picture=" + picture +
			", file=" + file +
			", remark=" + remark +
			"}";
	}
}
