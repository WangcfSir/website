package com.website.system.model.vo;

/**
 * 内容表（文件公告、行业动态、政策法规）
 * @author yanqb
 * @since 2018-11-18
 */
public class ArticleVO {

	private Integer id;

	/**
	 * 模块:1动态中心;2文件公告;3政策法规;4绿色建筑;0其他
	 */
	private Integer type;

    /**
     * 类型内容
     */
	private String typeName;

    /**
     * 创建人
     */
	private String createUser;

    /**
     * 发布时间
     */
	private String createTime;

    /**
     * 标题
     */
	private String title;

    /**
     * 项目内容
     */
	private String content;

	/**
	 * 状态:0已显示;1未显示;2暂存
	 */
	private Integer state;


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

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
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

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "ArticleVO{" +
				"id=" + id +
				", type='" + type + '\'' +
				", typeName='" + typeName + '\'' +
				", createUser='" + createUser + '\'' +
				", createTime=" + createTime +
				", title='" + title + '\'' +
				", content='" + content + '\'' +
				", state=" + state +
				'}';
	}
}
