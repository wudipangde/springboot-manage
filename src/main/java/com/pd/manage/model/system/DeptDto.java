package com.pd.manage.model.system;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class DeptDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;//
	private String name;//名称
	private Integer pid;//'父ID,默认第一次创建时
	private String url="dept/single";
	private Integer hiber;
	private Integer type;//0其它 ，1组，2栈
	private List<DeptDto> child=new ArrayList<DeptDto>();
	private Integer area;//
	private Integer province;//
	private String provinceName;//
	private String letter;//首字母
	private String nickName;//
	private Date startRunTime;//
}
