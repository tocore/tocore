package oasis;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class ExecutorsTest {
	/*
	@Test
	public void test() {		
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		Runnable task = ()->{
			System.out.println("inside : " + Thread.currentThread().getName());
		};
		executorService.submit(task);		
		executorService.shutdown();
	}
	
	@Test
	public void test2() {		
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		Runnable task = ()->{
			System.out.println("inside : " + Thread.currentThread().getName());
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (Exception e) {
				e.printStackTrace();
			}
		};
		executorService.submit(task);		
		executorService.shutdown();
	}
	
	
//	한번만 실행
	@Test
	public void test3() {		
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
		Runnable task = () -> {
			System.out.println("Executing Task At " + System.nanoTime());
		};
		
		System.out.println("Submitting task at " + System.nanoTime() + " to be executed after 5 seconds.");
		scheduledExecutorService.schedule(task, 5, TimeUnit.SECONDS);
		scheduledExecutorService.shutdown();
	}
	 */	
	
//	주기적으로 실행
	@Test
	public void test4() {		
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
		Runnable task = () -> {
			System.out.println("Executing at " + System.nanoTime());
		};
		System.out.println("scheduling task to be executed every 2 seconds with an initial delay of 0 seconds");
		scheduledExecutorService.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);		
	}
}
