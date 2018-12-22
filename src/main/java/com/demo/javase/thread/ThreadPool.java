package com.demo.javase.thread;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/11/6
 */
public class ThreadPool {
	public void useThreadPool(){
		long startTime=System.currentTimeMillis();
		final List<Integer> l =new LinkedList<>();
		ThreadPoolExecutor tp=new ThreadPoolExecutor(1, 1, 60, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
		final Random random =new Random();
	}
}
