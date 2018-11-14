package com.business.img.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.business.img.entity.ImgData;
import com.business.img.service.ImgDataService;
import com.github.pagehelper.PageInfo;
import com.phiz.common.base.BaseController;
import com.phiz.common.base.Result;

@RestController
@RequestMapping("/img/data")
public class ImgDataController extends BaseController {

	@Autowired
	private ImgDataService imgDataService;
	
	
	@RequestMapping("/page")
	public Result page(Integer pageSize,Integer pageNum,ImgData img) {
		PageInfo<ImgData> page = imgDataService.findPage(pageSize, pageNum, img);
		return SUCCESS(page);
	}
	
}
