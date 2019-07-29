package com.hcl.stockex;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StockexApplicationTests {

	String hello="hello";
	
	@Test
	public void contextLoads() {
		Assert.assertEquals(hello, hello);
	}

}
