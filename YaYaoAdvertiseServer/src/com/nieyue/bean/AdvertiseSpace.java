package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 广告位
 * 
 * @author yy
 * 
 */
public class AdvertiseSpace implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 广告位id
	 */
	private Integer advertiseSpaceId;
	/**
	 * 广告位号名称
	 */
	private String name;
	/**
	 * 投放平台
	 */
	private String platform;
	/**
	 * 类型
	 */
	private String type;
	/**
	 * 广告位置
	 */
	private String location;
	/**
	 * 广告位状态
	 */
	private String status;
	/**
	 * 更新时间
	 */
	private Date updateDate;
	/**
	 * 管理员id外键
	 */
	private Integer adminId;

	public AdvertiseSpace() {
		super();
	}

	public AdvertiseSpace(Integer advertiseSpaceId, String name,
			String platform, String type, String location, String status,
			Date updateDate, Integer adminId) {
		super();
		this.advertiseSpaceId = advertiseSpaceId;
		this.name = name;
		this.platform = platform;
		this.type = type;
		this.location = location;
		this.status = status;
		this.updateDate = updateDate;
		this.adminId = adminId;
	}

	public Integer getAdvertiseSpaceId() {
		return advertiseSpaceId;
	}

	public void setAdvertiseSpaceId(Integer advertiseSpaceId) {
		this.advertiseSpaceId = advertiseSpaceId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

}
