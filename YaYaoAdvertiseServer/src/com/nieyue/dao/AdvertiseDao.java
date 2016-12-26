package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.Advertise;

/**
 *  广告数据库接口
 * @author yy
 *
 */
public interface AdvertiseDao {
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
	public List<Advertise> browsePagingAdvertise(@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
}
