package com.pd.manage.service.system.impl;

import com.pd.manage.model.system.RoleDto;
import com.pd.manage.service.system.IRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/25
 */
@Service
public class RoleServiceImpl implements IRoleService {
	@Override
	public List<RoleDto> findAuthorityRoleByUserId(Integer uid, Integer type) {
		return null;
	}
}
