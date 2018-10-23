package com.pd.manage.model.system;

import lombok.Data;

import java.io.Serializable;

@Data
public class ButtonDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer mid;
	private String name;
	private String perms;
}
