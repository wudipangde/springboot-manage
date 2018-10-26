package com.pd.manage.service.system.impl;

import com.pd.manage.dao.system.IEmployeeDao;
import com.pd.manage.model.system.EmployeeDto;
import com.pd.manage.service.system.IEmployeeService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/25
 */
@Service
public class EmployeeServiceImpl implements IEmployeeService {
	@Resource
	private RedisTemplate redisTemplate;
	@Resource
	private IEmployeeDao employeeDao;

	@Override
	public EmployeeDto findEmployeeById(Integer id) {
		String key="erp:manage:employee:"+id;
		ValueOperations<String, EmployeeDto> operations = redisTemplate.opsForValue();
		// 缓存存在
		boolean hasKey = redisTemplate.hasKey(key);
		if (hasKey) {
			EmployeeDto employeeDto = (EmployeeDto) operations.get(key);
			System.out.println("EmployeeServiceImpl.findEmployeeById() : 从缓存中获取了员工 >> " + employeeDto.toString());
			return employeeDto;
		}
		//从数据库查询
		EmployeeDto employeeDto =employeeDao.findEmployeeById(id);
		operations.set(key,employeeDto);
		System.out.println("EmployeeServiceImpl.findEmployeeById() : 从数据库中获取了员工 >> " + employeeDto.toString());
		return employeeDto;
	}

	@Override
	public EmployeeDto findSysLogin(String email, String pwd, int type) {
		return null;
	}

	@Override
	public EmployeeDto findEmployeeByEmail(String email) {
		return null;
	}
}
