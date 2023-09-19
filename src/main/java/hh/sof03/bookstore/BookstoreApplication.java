package hh.sof03.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.sof03.bookstore.domain.Book;
import hh.sof03.bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demodata(BookRepository repository) {
		return (args) -> {
			log.info("save a few demo books");
			Book b1 = new Book("Rautakolmio", "Leena Lehtolainen", "9789520429348", 2021, 7.80);
			Book b2 = new Book("Ensimmäinen murhani", "Leena Lehtolainen", "9789520422301", 2020, 15.50);
			Book b3 = new Book("Harmin paikka", "Leena Lehtolainen", "9789520422318", 2020, 7.90);

			repository.save(b1);
			repository.save(b2);
			repository.save(b3);

			log.info("fetch all books");

		};
		// title, author, isbn, year, price
	}

}
