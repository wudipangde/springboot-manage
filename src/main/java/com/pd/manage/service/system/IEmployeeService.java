package com.pd.manage.service.system;


import com.pd.manage.model.system.EmployeeDto;
import org.springframework.stereotype.Service;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/25
 */
public interface IEmployeeService {
	/**
	 * 根据ID查询
	 * @return
	 */
	EmployeeDto findEmployeeById(Integer id);

	/**
	 * 邮箱+密码（后台系统登录）
	 * @param type 登录类型 ：1后台登录，2APP登录
	 */
	EmployeeDto findSysLogin(String email,String pwd,int type);

	/**
	 * 邮箱
	 */
	EmployeeDto findEmployeeByEmail(String email);
}
