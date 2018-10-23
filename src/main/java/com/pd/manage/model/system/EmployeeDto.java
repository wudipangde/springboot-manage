package com.pd.manage.model.system;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class EmployeeDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;//
	private String name;//名称
	private String img;
	private String code;
	private String email;
	private String password;
	private String phone;
	private String dept;//所属部门(包括上级部门)
	private String post;//所属职位
	private String pname;//所属上级名称
	private Integer grade;
	private String dept2;//所属部门(不包括上级部门)
	private Integer deptid;
	private Integer postid;
	private Integer pid;
	private Integer province;
	private String provinceName;
	private Integer area;//
	private Long zhsuserid;
	private String resume;
	private Integer pdeptid;//直属上级所在的部门ID
	private Integer deptpid;//所属部门的上级部门ID
	private Integer emplStatus;//员工状态：1在职,2离职，3预离职，4试用期，5实习，6兼职，7不在岗
	private Integer skillLevel;//技能等级：1初级, 2中级, 3高级, 3试用期, 5实习生
	private String emplDesc;//备注
	private String letter;
	private String joinDate;//入职时间
	private List<RoleDto> roleList;
	public boolean isAdmin()
	{
		return isAdmin(this.id);
	}
	public static boolean isAdmin(Integer id)
	{
		return id != null && 1L == id;
	}
}
