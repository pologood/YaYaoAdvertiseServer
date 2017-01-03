package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.AdvertiseSpaceData;

/**
 * 广告位数据逻辑层接口
 * @author yy
 *
 */
public interface AdvertiseSpaceDataService {
	/** 新增广告位数据 */	
	public boolean addAdvertiseSpaceData(AdvertiseSpaceData advertiseSpaceData) ;	
	/** 删除广告位数据 */	
	public boolean delAdvertiseSpaceData(Integer advertiseSpaceDataId) ;
	/** 更新广告位数据*/	
	public boolean updateAdvertiseSpaceData(AdvertiseSpaceData advertiseSpaceData);
	/** 装载广告位数据 */	
	public AdvertiseSpaceData loadAdvertiseSpaceData(Integer advertiseSpaceDataId);	
	/** 广告位数据总共数目 */	
	public int countAll();
	/** 分页广告位数据信息 */
	public List<AdvertiseSpaceData> browsePagingAdvertiseSpaceData(int pageNum,int pageSize,String orderName,String orderWay) ;
}
