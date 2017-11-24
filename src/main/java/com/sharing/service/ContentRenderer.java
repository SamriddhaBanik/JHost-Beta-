package com.sharing.service;

import java.io.File;

import com.sharing.response.model.UIEntity;
import com.sharing.storage.service.DisplayIdService;

public class ContentRenderer {

	public UIEntity renderFileDetails(File file) throws Exception{
		//String absolutePath=file.getAbsolutePath();
		//String actualPath=absolutePath.replace(new File(config.getGetEscapedActualPath()).getPath(), new File(config.getGetEscapedVirtualPath()).getPath());
		UIEntity details=new UIEntity();
		details.setDisplayName(file.getName());
		details.setSize("0 MB");
		details.setUuidString(DisplayIdService.add(file.getAbsolutePath()));
		if(!file.isDirectory()){
			details.setType(file.getName().substring(file.getName().lastIndexOf(".")));
			details.setDirectory(false);
		}
		else {
			details.setType("Directory");	
			details.setDirectory(true);
		}
		return details;
	}
}
