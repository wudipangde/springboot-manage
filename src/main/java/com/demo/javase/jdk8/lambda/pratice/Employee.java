package com.demo.javase.jdk8.lambda.pratice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wxw
 * @Description:XX
 * @date 2019/3/29
 */
@Data@AllArgsConstructor@NoArgsConstructor
public class Employee {
	private Integer id;
	private String name;
	private Integer age;
	private Double score;

	public Employee(String name) {
		this.name=name;
	}
}
