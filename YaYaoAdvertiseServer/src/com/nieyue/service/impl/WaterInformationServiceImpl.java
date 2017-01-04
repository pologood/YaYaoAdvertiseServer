package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nieyue.bean.WaterInformation;
import com.nieyue.dao.WaterInformationDao;
import com.nieyue.service.WaterInformationService;
@Service("waterInformationService")
public class WaterInformationServiceImpl implements WaterInformationService{
	@Resource
	WaterInformationDao waterInformationDao;

	@Override
	public boolean addWaterInformation(WaterInformation waterInformation) {
		waterInformation.setCreateDate(new Date());
		boolean b = waterInformationDao.addWaterInformation(waterInformation);
		return b;
	}

	@Override
	public boolean delWaterInformation(Integer waterInformationId) {
		boolean b = waterInformationDao.delWaterInformation(waterInformationId);
		return b;
	}

	@Override
	public boolean updateWaterInformation(WaterInformation waterInformation) {
		boolean b = waterInformationDao.updateWaterInformation(waterInformation);
		return b;
	}

	@Override
	public WaterInformation loadWaterInformation(Integer waterInformationId) {
		WaterInformation r = waterInformationDao.loadWaterInformation(waterInformationId);
		return r;
	}

	@Override
	public int countAll() {
		int c = waterInformationDao.countAll();
		return c;
	}

	@Override
	public List<WaterInformation> browsePagingWaterInformation(int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<WaterInformation> l = waterInformationDao.browsePagingWaterInformation(pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
