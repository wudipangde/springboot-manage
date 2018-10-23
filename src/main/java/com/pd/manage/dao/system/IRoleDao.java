package com.pd.manage.dao.system;

import com.pd.manage.model.system.RoleDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface IRoleDao {
	/**
	 * 根据用户id查询角色列表
	 */
	List<RoleDto> findAuthorityRoleByUserId(@Param("uid") Integer uid,@Param("type") Integer type);
}
