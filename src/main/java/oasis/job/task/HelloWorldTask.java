package oasis.job.task;

import org.quartz.DisallowConcurrentExecution;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * **************************************************
 * HelloWorldTask class
 * 스케쥴 테스트
 * **************************************************
 * </pre>
 * @File Name : HelloWorldTask.java 
 * @author : 
 * @date 
 * 
 */

@Slf4j
@Component
@DisallowConcurrentExecution // 중복실행 방지 - 실행중인경우 건너 뜀 
public class HelloWorldTask {		
    /**
	 * test 용  
	 */	
	@Scheduled(cron="${Hello.BATCH.TIME}")
	public void helloWorld() throws Exception {
		log.debug("hello world ~~");
	}
}
