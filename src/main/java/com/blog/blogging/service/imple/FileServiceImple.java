package com.blog.blogging.service.imple;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blog.blogging.service.FileService;
@Service
public class FileServiceImple implements FileService{

	@Override
	public String uploadImage(String path, MultipartFile file) {

		String fileName=file.getOriginalFilename();



		String randomId=UUID.randomUUID().toString();
		String finalPath = randomId.concat(fileName.substring(fileName.lastIndexOf(".")));

		String filePath=path+File.separator+finalPath;
		File f=new File(path);
		if(!f.exists()){
			f.mkdir();
		}


		try {
			Files.copy(file.getInputStream(), Paths.get(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}	

		return filePath;
	}

	@Override
	public InputStream getResource(String path, String fileName) {
	
		 String fullPath=path+File.separator+fileName;
		 FileInputStream is = null; 
		 try {
			 is = new FileInputStream(fullPath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	
		return is;
	}


}
 	 