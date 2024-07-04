package com.likelion.web;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WebApplicationTests {

	@Test
	void contextLoads() {
		ConsumerExample2 consumerExample2 = new ConsumerExample2();
		consumerExample2.main(null);		
	}

}

class ConsumerExample1 {
 
    static void printValue(int val) {
        System.out.println(val);
    }

}

class ConsumerExample2 {

    public static void main(String[] args) {
		ConsumerExample3 consumerExample3 = new ConsumerExample3();
		consumerExample3.main(args);
	}

}

class ConsumerExample3 {
 
    public static void main(String[] args) {
		System.err.println("Hello World!");
	}

}
