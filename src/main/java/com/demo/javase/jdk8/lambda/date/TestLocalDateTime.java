package com.demo.javase.jdk8.lambda.date;

import io.lettuce.core.output.ScanOutput;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Set;

/**
 * @author wxw
 * @Description:XX
 * @date 2019/3/29
 */
public class TestLocalDateTime {
	public void test1(){
		LocalDateTime ldt = LocalDateTime.now();
		LocalDateTime ldt1 = LocalDateTime.now(ZoneId.systemDefault());
		System.out.println(ldt);
		System.out.println(ldt1);
	}

	public void test2(){
		Set<String> set =ZoneId.getAvailableZoneIds();
		set.forEach(System.out::println);
	}

	public static void main(String[] args) {
		TestLocalDateTime tldt =new TestLocalDateTime();
		tldt.test1();
	}
}
