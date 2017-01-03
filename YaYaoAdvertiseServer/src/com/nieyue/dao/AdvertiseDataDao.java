package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.AdvertiseData;

/**
 * 广告数据数据库接口
 * @author yy
 *
 */
public interface AdvertiseDataDao {
	/** 新增广告数据*/	
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
	public List<AdvertiseData> browsePagingAdvertiseData(@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
}
