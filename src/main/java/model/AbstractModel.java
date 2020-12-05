package model;

import java.sql.Timestamp;

import service.IUserService;
import service.imp.UserService;

public class AbstractModel {
	private String id;
	private String name;
	private String alias;
	private String status;
	private String createBy, updateBy;
	private Timestamp createDate, updateDate;
	private IUserService userService = UserService.getInstance();
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public String getNameCreateBy(String id) {
		UserModel user = userService.findOneById(id);
		if (user == null) return "";
		return user.getFullName();
	}

	public String getNameUpdateBy(String id) {
		UserModel user = userService.findOneById(id);
		if (user == null) return "";
		return user.getFullName();
	}

	

}
