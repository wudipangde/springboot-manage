package com.demo.javase.jdk8.lambda.pratice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @author wxw
 * @Description:XX
 * @date 2019/3/28
 */
public class Grammar {
	public void test1(){
		int num =1 ;
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				System.out.println("hello world!"+num);
			}
		};
		runnable.run();
		//1.8
		Runnable runnable1 = ()->System.out.println("hello world!"+num);
		Consumer<String> con = x -> System.out.println(x);
		Consumer<String> con1 = System.out::println;

		Comparator<Integer> com = (x, y) -> {
			System.out.println("函数式接口");
			return Integer.compare(x, y);
		};
		Comparator<Integer> com1 = (x, y) -> {return Integer.compare(x, y);};
		Comparator<Integer> com2 = (x, y) -> Integer.compare(x, y);
		Comparator<Integer> com3 = Integer::compare;

	}

	public void test2(){
		Integer num = operation(100,x->x*x);
		System.out.println(num);
		System.out.println(operation(200,y->y+200));
	}

	public Integer operation(Integer num,MyFun mf){
		return mf.getValue(num);
	}

	void test3(){
		List<Employee> emps = Arrays.asList(
				new Employee(101, "张三", 18, 9999.99),
				new Employee(102, "李四", 59, 6666.66),
				new Employee(103, "王五", 28, 3333.33),
				new Employee(104, "赵六", 8, 7777.77),
				new Employee(105, "田七", 38, 5555.55)
		);

		emps.sort((e1, e2) -> {
			if (e1.getAge().equals(e2.getAge())) {
				return e1.getName().compareTo(e2.getName());
			} else {
				return Integer.compare(e1.getAge(), e2.getAge());
			}
		});

		for (Employee emp:emps){
			System.out.println(emp);
		}

		Function<String, Employee> fun = Employee::new;

	}

	void test4(){
		happy(100000,m-> System.out.println(m+"元"));
	}

	public void happy(double money,Consumer<Double> con){
		con.accept(money);
	}

	void test5(){
		List<Integer> numList=getNumList(10,()-> (int) (Math.random() * 100));
		for(Integer num:numList){
			System.out.println(num);
		};
	}

	public List<Integer> getNumList(int num, Supplier<Integer> sup){
		List<Integer> list =new ArrayList<>();
		for(int i=0;i<num;i++){
			Integer n = sup.get();
			list.add(n);
		}
		return list;
	}

	void test6(){
		List<String> list = new ArrayList<>();
		Stream<String> stream = list.stream(); //获取一个顺序流
		Stream<String> parallelStream = list.parallelStream(); //获取一个并行流
		//2. 通过 Arrays 中的 stream() 获取一个数组流
		Integer[] nums = new Integer[10];
		Stream<Integer> stream1 = Arrays.stream(nums);
		//3. 通过 Stream 类中静态方法 of()
		Stream<Integer> stream2 = Stream.of(1,2,3,4,5,6);
		//4. 创建无限流
		//迭代
		Stream<Integer> stream3 = Stream.iterate(0, (x) -> x + 2).limit(10);
		stream3.forEach(System.out::println);
		//生成
		Stream<Double> stream4 = Stream.generate(Math::random).limit(2);
		stream4.forEach(System.out::println);

	}

	void test7(){
		List<Employee> emps = Arrays.asList(
				new Employee(102, "李四", 59, 6666.66),
				new Employee(101, "张三", 18, 9999.99),
				new Employee(103, "王五", 28, 3333.33),
				new Employee(104, "赵六", 8, 7777.77),
				new Employee(104, "赵六", 8, 7777.77),
				new Employee(104, "赵六", 8, 7777.77),
				new Employee(105, "田七", 38, 5555.55)
		);
		Stream<Employee> stream = emps.stream().filter(e->{
			System.out.println("测试中间操作");
			return e.getAge()<=35;
		});
		stream.forEach(System.out::println);
	}

	void test8(){
		List<String> arrList = Arrays.asList("aaa","bbb","ccc","ddd","eeee");
		arrList.stream().map(String::toUpperCase).forEach(System.out::println);
	}

	void test9(){
		List<Integer> arrList = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

	}

	public static void main(String[] args) {
		Grammar grammar =new Grammar();
//		grammar.test2();
//		grammar.test3();
//		grammar.test4();
//		grammar.test6();
		grammar.test8();
	}
}
