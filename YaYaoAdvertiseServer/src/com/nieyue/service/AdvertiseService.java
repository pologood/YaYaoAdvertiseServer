package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.Advertise;

/**
 * 广告逻辑层接口
 * @author yy
 *
 */
public interface AdvertiseService {
	/** 新增广告 */	
	public boolean addAdvertise(Advertise advertise) ;	
	/** 删除广告 */	
	public boolean delAdvertise(Integer advertiseId) ;
	/** 更新广告*/	
	public boolean updateAdvertise(Advertise advertise);
	/** 装载广告 */	
	public Advertise loadAdvertise(Integer advertiseId);	
	/** 广告总共数目 */	
	public int countAll();
	/** 分页广告 */
	public List<Advertise> browsePagingAdvertise(int pageNum,int pageSize,String orderName,String orderWay) ;
}
