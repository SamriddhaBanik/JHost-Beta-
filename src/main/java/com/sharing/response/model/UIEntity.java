package com.sharing.response.model;

import java.io.Serializable;

public class UIEntity implements Serializable{

	private static final long serialVersionUID = -4139313918627955383L;
	private String displayName;
	private String size;
	private String uuidString;
	private String type;
	private boolean isDirectory;
	
	public boolean isDirectory() {
		return isDirectory;
	}
	public void setDirectory(boolean isDirectory) {
		this.isDirectory = isDirectory;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getUuidString() {
		return uuidString;
	}
	public void setUuidString(String uuidString) {
		this.uuidString = uuidString;
	}
	
}
