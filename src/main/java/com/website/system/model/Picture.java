package com.website.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 暂时针对首页的轮播图和无线滚动图
 * </p>
 *
 * @author yanqb
 * @since 2018-11-23
 */
public class Picture extends Model<Picture> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;

    /**
     * 生成的图片编码
     */
	@TableField("picture_id")
	private String pictureId;

    /**
     * 图片路径
     */
	@TableField("picture_url")
	private String pictureUrl;

	@TableField("modular_name")
	private String modularName;

    /**
     * 显示顺序
     */
	private Integer sort;

    /**
     * 1,轮播图；2,无线滚动；3,文章图片；
     */
	private Integer type;

    /**
     * 显示状态1显示；2不显示
     */
	private Integer state;

    /**
     * 文章链接
     */
	@TableField("article_id")
	private String articleId;

	/**
	 * 文章标题
	 */
	@TableField("article_title")
	private String articleTitle;

	/**
	 * 上传时间
	 */
	@TableField("upload_time")
	private Date uploadTime;

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

	public String getPictureId() {
		return pictureId;
	}

	public void setPictureId(String pictureId) {
		this.pictureId = pictureId;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getModularName() {
		return modularName;
	}

	public void setModularName(String modularName) {
		this.modularName = modularName;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
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

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Picture{" +
				"id=" + id +
				", pictureId='" + pictureId + '\'' +
				", pictureUrl='" + pictureUrl + '\'' +
				", modularName='" + modularName + '\'' +
				", sort=" + sort +
				", type=" + type +
				", state=" + state +
				", articleId='" + articleId + '\'' +
				", articleTitle='" + articleTitle + '\'' +
				", uploadTime=" + uploadTime +
				", modifyTime=" + modifyTime +
				'}';
	}
}
