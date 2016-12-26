package com.nieyue.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nieyue.bean.AdvertiseSpace;
import com.nieyue.exception.StateResult;
import com.nieyue.service.AdvertiseSpaceService;


/**
 * 问题控制类
 * @author yy
 *
 */
@Controller("advertiseSpaceController")
@RequestMapping("/advertiseSpace")
public class AdvertiseSpaceController {
	@Resource
	private AdvertiseSpaceService advertiseSpaceService;
	
	/**
	 * 问题分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<AdvertiseSpace> browsePagingAdvertiseSpace(
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="advertise_space_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay,HttpSession session)  {
			List<AdvertiseSpace> list = new ArrayList<AdvertiseSpace>();
			list= advertiseSpaceService.browsePagingAdvertiseSpace(pageNum, pageSize, orderName, orderWay);
			return list;
	}
	/**
	 * 问题修改
	 * @return
	 */
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateAdvertiseSpace(@ModelAttribute AdvertiseSpace advertiseSpace,HttpSession session)  {
		boolean um = advertiseSpaceService.updateAdvertiseSpace(advertiseSpace);
		return StateResult.getSR(um);
	}
	/**
	 * 问题增加
	 * @return 
	 */
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addAdvertiseSpace(@ModelAttribute AdvertiseSpace advertiseSpace, HttpSession session) {
		boolean am = advertiseSpaceService.addAdvertiseSpace(advertiseSpace);
		return StateResult.getSR(am);
	}
	/**
	 * 问题删除
	 * @return
	 */
	@RequestMapping(value = "/{advertiseSpaceId}/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delAdvertiseSpace(@PathVariable("advertiseSpaceId") Integer advertiseSpaceId,HttpSession session)  {
		boolean dm = advertiseSpaceService.delAdvertiseSpace(advertiseSpaceId);
		return StateResult.getSR(dm);
	}
	/**
	 * 问题浏览数量
	 * @return
	 */
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(HttpSession session)  {
		int count = advertiseSpaceService.countAll();
		return count;
	}
	/**
	 * 问题单个加载
	 * @return
	 */
	@RequestMapping(value = "/{advertiseSpaceId}", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody AdvertiseSpace loadAdvertiseSpace(@PathVariable("advertiseSpaceId") Integer advertiseSpaceId,HttpSession session)  {
		AdvertiseSpace advertiseSpace=new AdvertiseSpace();
		advertiseSpace = advertiseSpaceService.loadAdvertiseSpace(advertiseSpaceId);
		return advertiseSpace;
	}
	
}
