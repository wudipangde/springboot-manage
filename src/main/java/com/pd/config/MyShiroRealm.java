package com.pd.config;

import com.pd.manage.dao.system.IEmployeeDao;
import com.pd.manage.dao.system.IMenuDao;
import com.pd.manage.dao.system.IRoleDao;
import com.pd.manage.model.system.ButtonDto;
import com.pd.manage.model.system.EmployeeDto;
import com.pd.manage.model.system.MenuDto;
import com.pd.manage.model.system.RoleDto;
import com.pd.utils.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyShiroRealm extends AuthorizingRealm {
	private Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);

	@Autowired
	private IEmployeeDao employeeDao;
	@Resource
	private IRoleDao roleDao;
	@Resource
	private IMenuDao menuDao;


	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		logger.info("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
		EmployeeDto employee = (EmployeeDto) principals.getPrimaryPrincipal();
		// 角色列表
		Set<String> roles = new HashSet<String>();
		// 功能列表
		Set<String> menus = new HashSet<String>();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		// 管理员拥有所有权限
		if (employee.isAdmin())
		{
			info.addRole("admin");
			info.addStringPermission("*:*:*");
		}
		else
		{
			//获取用户角色
			List<RoleDto> rlist=roleDao.findAuthorityRoleByUserId(employee.getId(),1);
			//获取用户权限
			List<ButtonDto> blist=menuDao.findSysMenuButtonByUserId(employee.getId(),1);
			List<MenuDto> mlist=menuDao.findMenuByUserId(employee.getId(),1);

			for(RoleDto roleDto:rlist){
				roles.add(roleDto.getId().toString());
			}
			for(MenuDto menuDto:mlist){
				if(!StringUtils.isEmpty(menuDto.getPerms())){
					menus.add(menuDto.getPerms());
				}
			}
			for(ButtonDto buttonDto:blist){
				if(!StringUtils.isEmpty(buttonDto.getPerms())){
					menus.add(buttonDto.getPerms());
				}
			}
			// 角色加入AuthorizationInfo认证对象
			info.setRoles(roles);
			// 权限加入AuthorizationInfo认证对象
			info.setStringPermissions(menus);
		}
		return info;
	}

	/**
	 * 主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。
	 *
	 * @param token
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
			throws AuthenticationException {
		logger.info("MyShiroRealm.doGetAuthenticationInfo()");

		//获取用户的输入的账号.
		String username = (String)token.getPrincipal();
		logger.info("credentials:{}", token.getCredentials());

		//通过username从数据库中查找 User对象，如果找到，没找到.
		//实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
		EmployeeDto userInfo = employeeDao.findEmployeeByEmail(username);
		logger.info("----->>userInfo="+userInfo);
		if(userInfo == null){
			return null;
		}
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
				userInfo, //用户名
				userInfo.getPassword(), //密码
//				ByteSource.Util.bytes(userInfo.getCredentialsSalt()),//salt=username+salt
				getName()  //realm name
		);
		return authenticationInfo;
	}

}