package com.internetadvert.edu.Pojo;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class Image {

	 private CommonsMultipartFile image;

	public CommonsMultipartFile getImage() {
		return image;
	}

	public void setImage(CommonsMultipartFile image) {
		this.image = image;
	}
	
	 
}
