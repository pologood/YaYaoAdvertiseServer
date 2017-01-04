package com.nieyue.controller;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
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

import com.nieyue.bean.Admin;
import com.nieyue.bean.Advertise;
import com.nieyue.bean.WaterInformation;
import com.nieyue.exception.StateResult;
import com.nieyue.service.AdminService;
import com.nieyue.service.AdvertiseService;
import com.nieyue.service.WaterInformationService;


/**
 * 广告控制类
 * @author yy
 *
 */
@Controller("advertiseController")
@RequestMapping("/advertise")
public class AdvertiseController {
	@Resource
	private AdvertiseService advertiseService;
	@Resource
	private AdminService adminService;
	@Resource
	private WaterInformationService waterInformationService;
	
	/**
	 * 广告分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<Advertise> browsePagingAdvertise(
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="advertise_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay,HttpSession session)  {
			List<Advertise> list = new ArrayList<Advertise>();
			list= advertiseService.browsePagingAdvertise(pageNum, pageSize, orderName, orderWay);
			return list;
	}
	/**
	 * 根据adminId广告分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/list/admin", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<Advertise> browsePagingAdvertiseByAdminId(
			@RequestParam(value="adminId")Integer adminId,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="advertise_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay,HttpSession session)  {
		List<Advertise> list = new ArrayList<Advertise>();
		list= advertiseService.browsePagingAdvertiseByAdminId(adminId, pageNum, pageSize, orderName, orderWay);
		return list;
	}
	/**
	 * 广告修改
	 * @return
	 */
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateAdvertise(@ModelAttribute Advertise advertise,HttpSession session)  {
		if(advertise.getStatus().equals("已结束")){
			return StateResult.getFail();
		}
		//消耗完或者时间到
		if(advertise.getNowUnitDeliveryNumber()>=advertise.getUnitDeliveryNumber()
				||advertise.getEndDeliveryDate().before(new Date())){
			advertise.setStatus("已结束");
			Admin b = adminService.loadAdmin(advertise.getAdminId());
			double nowMoney = b.getMoney()-advertise.getNowUnitMoney();//金钱合并
			boolean um = adminService.moneyAdmin(advertise.getAdminId(),nowMoney);
			if(um){
				WaterInformation waterInformation=new WaterInformation();
				waterInformation.setAdminId(advertise.getAdminId());
				waterInformation.setName(advertise.getName());
				waterInformation.setType("消耗");
				waterInformation.setMoney(advertise.getNowUnitMoney());
				//保存流水信息
				waterInformationService.addWaterInformation(waterInformation);
			}
			return StateResult.getSuccess();
		}
		
		boolean um = advertiseService.updateAdvertise(advertise);
		return StateResult.getSR(um);
	}
	/**
	 * 广告增加
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addAdvertise(@ModelAttribute Advertise advertise, HttpSession session) {
			if(advertise.getEndDeliveryDate().before(new Date())){
					return StateResult.getFail();
				}
		
		boolean am = advertiseService.addAdvertise(advertise);
		return StateResult.getSR(am);
	}
	/**
	 * 广告删除
	 * @return
	 */
	@RequestMapping(value = "/{advertiseId}/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delAdvertise(@PathVariable("advertiseId") Integer advertiseId,HttpSession session)  {
		boolean dm = advertiseService.delAdvertise(advertiseId);
		return StateResult.getSR(dm);
	}
	/**
	 * 广告浏览数量
	 * @return
	 */
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(HttpSession session)  {
		int count =advertiseService.countAll();
		return count;
	}
	/**
	 * 根据adminId广告浏览数量
	 * @return
	 */
	@RequestMapping(value = "/count/{adminId}", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAllByAdminId(@PathVariable("adminId") Integer adminId,HttpSession session)  {
		int count =advertiseService.countAllByAdminId(adminId);
		return count;
	}
	/**
	 * 广告单个加载
	 * @return
	 */
	@RequestMapping(value = "/{advertiseId}", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Advertise loadAdvertise(@PathVariable("advertiseId") Integer advertiseId,HttpSession session)  {
		Advertise advertise=new Advertise();
		advertise = advertiseService.loadAdvertise(advertiseId);
		return advertise;
	}
}
