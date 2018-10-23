package com.pd.manage.model.system;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Data
public class MenuDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer pid;
	private String name;
	private String url;
	private Integer hiber;
	private String code;
	private String ico;
	private String perms;
	private List<MenuDto> child=new ArrayList<MenuDto>();
}
