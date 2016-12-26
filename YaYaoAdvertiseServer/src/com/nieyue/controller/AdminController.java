package com.nieyue.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nieyue.bean.Admin;
import com.nieyue.bean.Role;
import com.nieyue.exception.StateResult;
import com.nieyue.service.AdminService;
import com.nieyue.service.JurisdictionService;
import com.nieyue.service.RoleService;
import com.nieyue.token.TokenManager;
import com.nieyue.token.TokenModel;
import com.nieyue.util.MyDESutil;


/**
 * 管理员控制类
 * @author yy
 *
 */
@Controller("adminController")
@RequestMapping("/admin")
public class AdminController {
	@Resource
	private AdminService adminService;
	@Autowired
	private TokenManager tokenManager;
	@Autowired
	private RoleService roleService;
	@Autowired
	private JurisdictionService jurisdictionService;
	
	/**
	 * 管理员分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<Admin> browsePagingAdmin(
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="admin_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay,HttpSession session)  {
			List<Admin> list = new ArrayList<Admin>();
			list= adminService.browsePagingAdmin(pageNum, pageSize, orderName, orderWay);
			return list;
	}
	/**
	 * 管理员全部查询
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/list/all", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<Admin> browseAllAdmin(
			@RequestParam(value="orderName",required=false,defaultValue="admin_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay,HttpSession session)  {
		List<Admin> list = new ArrayList<Admin>();
		list= adminService.browseAllAdmin( orderName, orderWay);
		return list;
	}
	/**
	 * 管理管理员修改
	 * @return
	 */
	@RequestMapping(value = "/update/all", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateAdminAll(@ModelAttribute Admin admin,HttpSession session)  {
		//不能添加相同的手机号或者邮箱
		List<String> lp = adminService.browseAllAdminPhone();
		List<String> le = adminService.browseAllAdminEmail();
		Admin oa = adminService.loadAdmin(admin.getAdminId());//自身原来的
		if(!oa.getCellPhone().equals(admin.getCellPhone())){
		for (int i = 0; i < lp.size(); i++) {
			if(lp.get(i).equals(admin.getCellPhone())){
				return StateResult.getSlefSR(40002, "手机号已经存在");
			}
		}
		}
		if(!oa.getEmail().equals(admin.getEmail())){
		for (int i = 0; i < le.size(); i++) {
			if(le.get(i).equals(admin.getEmail())){
				return StateResult.getSlefSR(40002, "email已经存在");
			}
		}
		}
		admin.setPassword(MyDESutil.getMD5(admin.getPassword()));
		boolean um = adminService.updateAdmin(admin);
		return StateResult.getSR(um);
	}
	/**
	 * 管理员修改
	 * @return
	 */
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateAdmin(@ModelAttribute Admin admin,HttpSession session)  {
		//不能添加相同的手机号或者邮箱
		List<String> lp = adminService.browseAllAdminPhone();
		List<String> le = adminService.browseAllAdminEmail();
		Admin oa = adminService.loadAdmin(admin.getAdminId());//自身原来的
		//如果修改的不等于原来的，才验证
		if(!oa.getCellPhone().equals(admin.getCellPhone())){
			for (int i = 0; i < lp.size(); i++) {
				if(lp.get(i).equals(admin.getCellPhone())){
					return StateResult.getSlefSR(40002, "手机号已经存在");
				}
			}
		}
		if(!oa.getEmail().equals(admin.getEmail())){
		for (int i = 0; i < le.size(); i++) {
			if(le.get(i).equals(admin.getEmail())){
				return StateResult.getSlefSR(40002, "email已经存在");
			}
		}
		}
		boolean um = adminService.updateAdmin(admin);
		return StateResult.getSR(um);
	}
	/**
	 * 修改密码
	 * @return
	 */
	@RequestMapping(value = "/update/password", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updatePasswordAdmin(@RequestParam(value="password")String password,@RequestParam(value="newpassword")String newpassword,HttpSession session)  {
		boolean um=false;
		if(session.getAttribute("admin")==null){
			return StateResult.getSR(um);
		}
		Admin admin = adminService.loadAdmin(((Admin) session.getAttribute("admin")).getAdminId());
			if(!admin.getPassword().equals(MyDESutil.getMD5(password))){
				return StateResult.getSR(um);
			}
			
			admin.setPassword(MyDESutil.getMD5(newpassword));
			um = adminService.updateAdmin(admin);
		return StateResult.getSR(um);
	}
	/**
	 * 管理员增加
	 * @return 
	 */
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addAdmin(@ModelAttribute Admin admin, HttpSession session) {
		//不能添加相同的手机号或者邮箱
		List<String> lp = adminService.browseAllAdminPhone();
		List<String> le = adminService.browseAllAdminEmail();
		for (int i = 0; i < lp.size(); i++) {
			if(lp.get(i).equals(admin.getCellPhone())){
				return StateResult.getSlefSR(40002, "手机号已经存在");
			}
		}
		for (int i = 0; i < le.size(); i++) {
			if(le.get(i).equals(admin.getEmail())){
				return StateResult.getSlefSR(40002, "email已经存在");
			}
		}
		admin.setPassword( MyDESutil.getMD5(admin.getPassword()));
		boolean am = adminService.addAdmin(admin);
		return StateResult.getSR(am);
	}
	/**
	 *管理员 删除
	 * @return
	 */
	@RequestMapping(value = "/{adminId}/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delAdmin(@PathVariable("adminId") Integer adminId,HttpSession session)  {
		boolean dm = adminService.delAdmin(adminId);
		return StateResult.getSR(dm);
	}
	/**
	 * 管理员浏览数量
	 * @return
	 */
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(HttpSession session)  {
		int count = adminService.countAll();
		return count;
	}
	/**
	 * 管理员单个加载
	 * @return
	 */
	@RequestMapping(value = "/{adminId}", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Admin loadAdmin(@PathVariable("adminId") Integer adminId,HttpSession session)  {
		Admin admin=new Admin();
		admin = adminService.loadAdmin(adminId);
		return admin;
	}
	/**
	 *管理员登录
	 * @return
	 */
	
	@RequestMapping(value = "/login", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Admin loginAdmin(@RequestParam(value="adminName")String adminName,@RequestParam(value="password")String password,HttpServletRequest request,HttpServletResponse response)  {
		Admin admin =new Admin();
		String md5pwd = MyDESutil.getMD5(password);
		admin= adminService.loginAdmin(adminName, md5pwd);
		if(admin!=null){
			 //生成一个token，保存用户登录状态
	        // tokenManager.createToken("XuDeOAadmin",admin.getAdminId(),request,response);
			request.getSession().setAttribute("admin", admin);
			Role role = roleService.loadRole(admin.getRoleId());
			request.getSession().setAttribute("role", role);
			
		}
		return admin;
	}

	/**
	 *管理员登出
	 * @return
	 */
	@RequestMapping(value = "/loginout", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody void loginoutAdmin(HttpServletRequest request,HttpServletResponse response)  {
		 //tokenManager.deleteToken("XuDeOAadmin",request,response);
		request.getSession().invalidate();
	}
	/**
	 *管理员状态
	 * @return
	 */
	@RequestMapping(value = "/islogin", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody boolean isloginAdmin(HttpServletRequest request,HttpServletResponse response)  {
		//if(tokenManager.checkToken("XuDeOAadmin", tokenManager.getToken("XuDeOAadmin", request), request,response)){
			if(request.getSession().getAttribute("admin")!=null){
			return true;
		}
		return false;
	}
	/**
	 *获取token
	 * @return
	 */
	@RequestMapping(value = "/token", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody TokenModel tokenAdmin(HttpServletRequest request,HttpServletResponse response)  {
		return tokenManager.getToken("XuDeOAadmin", request);
	}
	
}
