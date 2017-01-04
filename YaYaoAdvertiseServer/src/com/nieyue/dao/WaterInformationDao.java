package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.WaterInformation;

/**
 * 流水信息数据库接口
 * @author yy
 *
 */
public interface WaterInformationDao {
	/** 新增流水信息*/	
	public boolean addWaterInformation(WaterInformation waterInformation) ;	
	/** 删除流水信息 */	
	public boolean delWaterInformation(Integer waterInformationId) ;
	/** 更新流水信息*/	
	public boolean updateWaterInformation(WaterInformation waterInformation);
	/** 装载流水信息 */	
	public WaterInformation loadWaterInformation(Integer waterInformationId);	
	/** 流水信息总共数目 */	
	public int countAll();	
	/** 分页流水信息信息 */
	public List<WaterInformation> browsePagingWaterInformation(@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
}
