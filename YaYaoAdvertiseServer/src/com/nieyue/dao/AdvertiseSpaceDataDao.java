package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.AdvertiseSpaceData;

/**
 * 广告位数据数据库接口
 * @author yy
 *
 */
public interface AdvertiseSpaceDataDao {
	/** 新增广告位数据*/	
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
	public List<AdvertiseSpaceData> browsePagingAdvertiseSpaceData(@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
}
