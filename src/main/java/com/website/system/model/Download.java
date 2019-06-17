package com.website.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * 下载中心
 *
 * @author yanqb
 * @since 2018-12-03
 */
public class Download extends Model<Download> {

	private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;

	/**
	 * 文件保存后的id
	 */
	@TableField("file_id")
	private Integer fileId;

	/**
	 * 文件名称
	 */
	@TableField("file_name")
	private String fileName;

	/**
	 * 对应文章
	 */
	@TableField("article_id")
	private Integer articleId;

	/**
	 * 对应文章标题
	 */
	@TableField("article_title")
	private String articleTitle;

	/**
	 * 文件保存后的路径
	 */
	@TableField("file_url")
	private String fileUrl;

	/**
	 * 上传时间
	 */
	@TableField("upload_time")
	private Date uploadTime;

	/**
	 * 上传人
	 */
	@TableField("upload_user")
	private String uploadUser;

	/**
	 * 状态：1显示，2不显示
	 */
	private Integer state;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getUploadUser() {
		return uploadUser;
	}

	public void setUploadUser(String uploadUser) {
		this.uploadUser = uploadUser;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Download{" +
				"id=" + id +
				", fileId=" + fileId +
				", fileName=" + fileName +
				", articleId=" + articleId +
				", articleTitle=" + articleTitle +
				", fileUrl=" + fileUrl +
				", uploadTime=" + uploadTime +
				", uploadUser=" + uploadUser +
				", state=" + state +
				"}";
	}
}
