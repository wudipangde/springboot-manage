package com.pd.manage.dao.system;

import com.pd.manage.model.system.EmployeeDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface IEmployeeDao {
	/**
	 * 根据ID查询
	 * @return
	 */
	EmployeeDto findEmployeeById(@Param("id") Integer id);

	/**
	 * 邮箱+密码（后台系统登录）
	 * @param type 登录类型 ：1后台登录，2APP登录
	 */
	EmployeeDto findSysLogin(@Param("email")String email,@Param("pwd")String pwd,@Param("type")int type);

	/**
	 * 邮箱
	 */
	EmployeeDto findEmployeeByEmail(@Param("email")String email);

}
