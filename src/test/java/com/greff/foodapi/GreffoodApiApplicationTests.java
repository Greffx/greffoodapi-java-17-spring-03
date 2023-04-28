package com.greff.foodapi;

//Junit is a simple library to execute test code, can write API and integration tests too

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest //supply with spring boot functions for tests
class GreffoodApiApplicationTests {

	@Test //every test method has this annotation, that's how is recognized as a test method
	void contextLoads() {
	}

}
