package com.demo.javase.jdk8.lambda.collection;

import com.demo.javase.designmode.iterator.MyCollection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;


/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/22
 */
public class Test1 {
	public static void main(String[] args) {
		Ilambda1 ilambda1= () -> System.out.println("测试无参");
		ilambda1.print();

		Ilambda2 ilambda2 = s -> System.out.println(s);
		Ilambda2 ilambda3 = System.out::println;
		ilambda2.print("有一个参数");
		ilambda3.print("---有一个参数");

		List<Integer> list =new ArrayList<>();
		List<Integer> list2 =new ArrayList<>();
		list.add(50);
		list.add(18);
		list.add(99);
		list.add(32);
		list2.add(50);
		list2.add(18);
		list2.add(99);
		list2.add(32);
		System.out.println(list.toString()+"---排序之前");
		System.out.println(list2.toString()+"---list2排序之前");
		Ilambda3 lambda4 =Collections::sort;
		lambda4.sort(list,(a,b)->{
			return a-b;
		});
		lambda4.sort(list2, Comparator.comparingInt(a -> a));
		System.out.println(list.toString()+"排序之后");
		System.out.println(list2.toString()+"list2排序之后");

		Predicate<String> predicate =(s) ->s.length()>0;
		System.out.println(predicate.test("foo"));
		System.out.println(predicate.negate().test("foo"));
		Predicate<Boolean> notNull =Objects::nonNull;
		Predicate<Boolean> isNull =Objects::isNull;
		Predicate<String> isEmpty =String::isEmpty;
		Predicate<String> isNotEmpty =isEmpty.negate();
		System.out.println(notNull.test(null));
		System.out.println(isNull.test(null));
		System.out.println(isEmpty.test("sss"));
		System.out.println(isNotEmpty.test(""));

		Optional<String> optional =Optional.of("bam");
		System.out.println("Optional isPresent=="+optional.isPresent());
		System.out.println("Optional get =="+optional.get());
		System.out.println("Optional fallback=="+optional.orElse("fallback"));
		optional.ifPresent(s-> System.out.println(s.charAt(0)));
	}
}
