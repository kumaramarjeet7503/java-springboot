package com.springbootjunit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class SpringbootJunitApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void calculate(){
		int a = 10 ;
		int b = 10 ;

		assertEquals(a, b);
	}

}
