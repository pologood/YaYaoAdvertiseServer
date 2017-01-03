package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.AdvertiseData;

/**
 * 广告数据逻辑层接口
 * @author yy
 *
 */
public interface AdvertiseDataService {
	/** 新增广告数据 */	
	public boolean addAdvertiseData(AdvertiseData advertiseData) ;	
	/** 删除广告数据 */	
	public boolean delAdvertiseData(Integer advertiseDataId) ;
	/** 更新广告数据*/	
	public boolean updateAdvertiseData(AdvertiseData advertiseData);
	/** 装载广告数据 */	
	public AdvertiseData loadAdvertiseData(Integer advertiseDataId);	
	/** 广告数据总共数目 */	
	public int countAll();
	/** 分页广告数据信息 */
	public List<AdvertiseData> browsePagingAdvertiseData(int pageNum,int pageSize,String orderName,String orderWay) ;
}
