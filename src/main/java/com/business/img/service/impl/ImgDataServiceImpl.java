package com.business.img.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.img.dao.ImgDataDao;
import com.business.img.entity.ImgData;
import com.business.img.service.ImgDataService;
import com.phiz.common.base.BaseServiceImpl;

@Service
public class ImgDataServiceImpl extends BaseServiceImpl<ImgData> implements ImgDataService{

	@Autowired
	public ImgDataServiceImpl(ImgDataDao dao) {
		super(dao);
	}

	
	
}
