package com.practice.aws.rds.example;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.SneakyThrows;

@SpringBootApplication
@RestController
@RequestMapping("/books")
public class SpringbootAwsRdsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootAwsRdsApplication.class, args);
	}

	@Autowired
	BookRepository bookRepository;

	@PostMapping("/book")
	public Book saveBook(@RequestBody Book book) {
		return bookRepository.save(book);
	}

	@GetMapping("/fetchbooks")
	public List<Book> findBooks() {
		return (List<Book>) bookRepository.findAll();
	}

	@SneakyThrows
	@GetMapping("/{bookId}")
	public Book findBook(@PathVariable int bookId) throws Exception {
		Book b = bookRepository.findById(bookId).orElseThrow(() -> new Exception("Book not found"));
		return b;

	}

}
