package com.project.bookmanager;

import org.junit.jupiter.api.Test;
import org.project.bookmanager.entity.Book;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {TestConfig.class})
class BookManagerApplicationTests {

	@Test
	void contextLoads() {
		bookPriceTest();
	}

	public void bookPriceTest() {
		var book = new Book(
			"",
			0L,
			9998888025007L,
			"",
			0L,
			0L,
			""
		);
		assertEquals(2500L,book.price());
	}
}
