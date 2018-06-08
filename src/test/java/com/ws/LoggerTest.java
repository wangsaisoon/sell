package com.ws;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {

	private final static Logger logger = LoggerFactory.getLogger(LoggerTest.class);

	@Test
	public void test1() {
		logger.debug("debug...");
		logger.info("info...");
		logger.error("error...");
	}

	@Test
	public void test2() {
		log.debug("debug---");
		log.info("info---输出变量：");
		String name = "小希";
		log.error("error---name:{}", name);
		log.warn("warn---");
	}
}
