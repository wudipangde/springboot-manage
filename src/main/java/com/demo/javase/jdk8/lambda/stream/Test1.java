package com.demo.javase.jdk8.lambda.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/22
 */
public class Test1 {
	/**
	 * foreach map filter limit sorted
	 */
	public static void main(String[] args) {
		Stream<Integer> integerStream =Stream.of(1,2,3,5);
		Stream<String> stringStream =Stream.of("taobao");

		List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
		List<String> filtered = strings.stream().filter(string->!string.isEmpty()).collect(Collectors.toList());
		strings.forEach(System.out::println);

		List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
		// 获取对应的平方数
		List<Integer> squaresList = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
		squaresList.forEach(System.out::println);

		Random random = new Random();
		random.ints().limit(10).sorted().forEach(System.out::println);

		List<Integer> collection = new ArrayList<Integer>();
		collection.add(14);
		collection.add(5);
		collection.add(43);
		collection.add(89);
		collection.add(64);
		collection.add(112);
		collection.add(55);
		collection.add(55);
		collection.add(58);
		System.out.println(collection.parallelStream().count());
		System.out.println(collection.parallelStream().max((a,b)->{return a-b;}).get());
		System.out.println(collection.parallelStream().min((a,b)->{return a-b;}).get());
		System.out.println(collection.stream().filter(Objects::nonNull).filter(num-> num >50).count());
		collection.stream().distinct().forEach(System.out::println);
	}
}
