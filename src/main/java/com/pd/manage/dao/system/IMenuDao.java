package com.pd.manage.dao.system;

import com.pd.manage.model.system.ButtonDto;
import com.pd.manage.model.system.MenuDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface IMenuDao {

	/**
	 * 根据用户ID加载后台系统菜单
	 * @param uid
	 * @param type
	 * @return
	 */
	List<MenuDto> findMenuByUserId(@Param("uid") Integer uid,@Param("type") Integer type);

	/**
	 * 根据用户ID加载后台系统菜单的功能按键
	 * @param uid
	 * @param type
	 * @return
	 */
	List<ButtonDto> findSysMenuButtonByUserId(@Param("uid") Integer uid,@Param("type") Integer type);

	/**
	 * 根据用户ID加载后台系统菜单
	 * @param uid
	 * @param type
	 * @return
	 */
	List<MenuDto> findSysMenu(@Param("uid") Integer uid,@Param("type") Integer type);

	/**
	 * 根据用户ID加载后台系统菜单的功能按键
	 * @param uid
	 * @param type
	 * @return
	 */
	List<ButtonDto> findSysMenuButton(@Param("uid") Integer uid,@Param("type") Integer type);
	
	/**
	 * 根据用户ID加载业务菜单
	 * @param uid
	 * @return
	 */
	List<MenuDto> findAppMenu(Integer uid);
}
