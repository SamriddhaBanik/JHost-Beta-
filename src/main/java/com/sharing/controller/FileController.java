package com.sharing.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.sharing.response.model.UIEntity;
import com.sharing.service.ContentRenderer;
import com.sharing.service.FileFinderService;
import com.sharing.storage.service.DisplayIdService;
import com.sharing.util.SystemConstants;

@RequestMapping("/file")
@RestController
public class FileController {

	@ResponseBody
	@RequestMapping(value="/download/{encrypted_filename}",method=RequestMethod.GET)
	public ResponseEntity<Resource> download(@PathVariable("encrypted_filename") String encryptedString) throws Exception{
		System.out.println("Encrypted Value : "+encryptedString);
		File file=new File(DisplayIdService.get(encryptedString));
		 Path path = Paths.get(file.getAbsolutePath());
		    ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
		 HttpHeaders headers=new HttpHeaders();   
		 	if(!file.isDirectory()){
		 		headers.set("Content-Disposition", "attachment; filename=download." + file.getName().substring(file.getName().lastIndexOf(".")));
		 	}
		    return ResponseEntity.ok()
		            .headers(headers)
		            .contentLength(file.length())
		            .contentType(MediaType.parseMediaType("application/octet-stream"))
		            .body(resource);
	}
	
	@ResponseBody
	@RequestMapping(value="/encrypt",method=RequestMethod.POST)
	public String encrypt(String filename) throws Exception{
		return DisplayIdService.add(filename);
	}
	
	@ResponseBody
	@RequestMapping(value="/init/metadata",method=RequestMethod.GET)
	public String getInitMetadata() throws Exception{
		return DisplayIdService.add(SystemConstants.fileBasePath);
	}
	
	@ResponseBody
	@RequestMapping(value="/metadata/{directory_hash}",method=RequestMethod.GET)
	public String dataDetails(@PathVariable("directory_hash") String directoryHash){
		Set<UIEntity> detailsSet=new HashSet<>();
		ContentRenderer renderer=new ContentRenderer();
		try {
		FileFinderService fileService=new FileFinderService();	
		Set<File> files=fileService.getFilesAndDirectory(DisplayIdService.get(directoryHash));
		
		files.stream().forEach(file->{
			try {
				detailsSet.add(renderer.renderFileDetails(file));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Gson().toJson(detailsSet);
	}
	
	
}
