package com.pd.manage.controller;

import com.pd.manage.dao.system.IEmployeeDao;
import com.pd.manage.dao.system.IMenuDao;
import com.pd.manage.dao.system.IRoleDao;
import com.pd.manage.model.system.ButtonDto;
import com.pd.manage.model.system.EmployeeDto;
import com.pd.manage.model.system.MenuDto;
import com.pd.manage.model.system.RoleDto;
import com.pd.manage.service.system.IEmployeeService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 首页控制器
 */
@Controller
public class HomeController {

    private Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Resource
	private IRoleDao roleDao;
	@Resource
	private IMenuDao menuDao;
	@Resource
	private IEmployeeService employeeService;

    @RequestMapping({"/","/index"})
    public String index(){
        return"/index";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, Map<String, Object> map) throws Exception{
        logger.info("HomeController.login()");
        
        // 登录失败从request中获取shiro处理的异常信息。
        // shiroLoginFailure:就是shiro异常类的全类名.
        String exception = (String) request.getAttribute("shiroLoginFailure");
        logger.info("exception=" + exception);
        String msg = "";
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                logger.info("UnknownAccountException -- > 账号不存在：");
                msg = "UnknownAccountException -- > 账号不存在：";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                logger.info("IncorrectCredentialsException -- > 密码不正确：");
                msg = "IncorrectCredentialsException -- > 密码不正确：";
            } else if ("kaptchaValidateFailed".equals(exception)) {
                logger.info("kaptchaValidateFailed -- > 验证码错误");
                msg = "kaptchaValidateFailed -- > 验证码错误";
            } else {
                msg = "else >> "+exception;
                logger.info("else -- >" + exception);
            }
        }
        map.put("msg", msg);
        // 此方法不处理登录成功,由shiro进行处理
        return "/login";
    }

    @RequestMapping("/403")
    public String unauthorizedRole(){
        logger.info("------没有权限-------");
        return "403";
    }
	@RequiresPermissions("system:post:view")
	@GetMapping("/getRoleList")
	public Map<String,List> getEmployee() {
		EmployeeDto employeeDto = (EmployeeDto) (SecurityUtils.getSubject().getPrincipal());
		Integer currentUserId = employeeDto.getId();
		Map<String,List> map =new HashMap<>();
		//获取用户角色
		List<RoleDto> rlist=roleDao.findAuthorityRoleByUserId(currentUserId,1);
		//获取用户权限
		List<ButtonDto> blist=menuDao.findSysMenuButtonByUserId(currentUserId,1);
		List<MenuDto> mlist=menuDao.findMenuByUserId(currentUserId,1);
		map.put("rlist",rlist);
		map.put("blist",blist);
		map.put("mlist",mlist);
		return map;
	}

	@GetMapping("/getEmployeeInfo")
	@ResponseBody
	public EmployeeDto getEmployeeInfo() {
		EmployeeDto employeeDto = (EmployeeDto) (SecurityUtils.getSubject().getPrincipal());
		Integer currentUserId = employeeDto.getId();
		return employeeService.findEmployeeById(currentUserId);
	}

}