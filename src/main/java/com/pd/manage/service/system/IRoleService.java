package com.pd.manage.service.system;

import com.pd.manage.model.system.RoleDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/25
 */
public interface IRoleService {
	/**
	 * 根据用户id查询角色列表
	 */
	List<RoleDto> findAuthorityRoleByUserId(Integer uid,Integer type);
}
