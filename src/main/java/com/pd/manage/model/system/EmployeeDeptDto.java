package com.pd.manage.model.system;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class EmployeeDeptDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer empId;//
	private Integer deptId;//
	private List<Integer> deptIds=new ArrayList<Integer>();
	private List<Integer> empIds=new ArrayList<Integer>();
}
