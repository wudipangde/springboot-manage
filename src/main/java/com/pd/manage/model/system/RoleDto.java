package com.pd.manage.model.system;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class RoleDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;//
	private String name;//
	private String des=null;//
	private Integer status;//
	private Integer type;//1系统，2业务
	private List<Integer> menu=new ArrayList<Integer>();
	private List<Integer> button=new ArrayList<Integer>();
}
