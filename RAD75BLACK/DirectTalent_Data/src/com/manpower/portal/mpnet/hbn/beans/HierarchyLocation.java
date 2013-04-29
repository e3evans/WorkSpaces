package com.manpower.portal.mpnet.hbn.beans;

import java.io.Serializable;


public class HierarchyLocation implements Serializable {

	private static final long serialVersionUID = -4530475062915485411L;
	
	private long id;
	private long parentId;
	private String locationName = null;
	private String parentName = null;
	private String language = null;
	private String locationPath = null;
	private String locationIdPath = null;
	
	private int level = 0;
	private boolean leaf = false;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getLocationPath() {
		return locationPath;
	}
	public void setLocationPath(String locationPath) {
		this.locationPath = locationPath;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getLocationIdPath() {
		return locationIdPath;
	}
	public void setLocationIdPath(String locationIdPath) {
		this.locationIdPath = locationIdPath;
	}
	public long getParentId() {
		return parentId;
	}
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
}
