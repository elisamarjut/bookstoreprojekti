package hh.sof03.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.sof03.bookstore.domain.Book;
import hh.sof03.bookstore.domain.BookRepository;
import hh.sof03.bookstore.domain.Category;
import hh.sof03.bookstore.domain.CategoryRepository;
import hh.sof03.bookstore.domain.User;
import hh.sof03.bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demodata(BookRepository bookRepository, CategoryRepository categoryRepository,
			UserRepository userRepository) {
		return (args) -> {
			log.info("Save a few categories");
			Category c1 = new Category("Crime");
			Category c2 = new Category("Romance");
			Category c3 = new Category("Scifi");

			categoryRepository.save(c1);
			categoryRepository.save(c2);
			categoryRepository.save(c3);

			log.info("Save a few books");
			Book b1 = new Book("Ensimmäinen murhani", "Leena Lehtolainen", "9789520422301", 2020, 7.80, c1);
			Book b2 = new Book("The Fault in Our Stars", "John Green", "9780141345659", 2013, 10.80, c2);
			Book b3 = new Book("Teemestarin kirja", "Emmi Itäranta", "9789518519747", 2019, 20.80, c3);

			bookRepository.save(b1);
			bookRepository.save(b2);
			bookRepository.save(b3);

			log.info("Fetch all categories");
			for (Category category : categoryRepository.findAll()) {
				log.info(category.toString());
			}

			log.info("Fetch all books");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}

			// Creating users: admin/admin, user/user, elisa/elisa
			User user1 = new User("user", "$2a$10$7fr9uCy6IlMhzmVQUNZtaOWjuaMdWB56m.yswQw/1/NojLe4fLWsS",
					"user@mail.com", "USER");
			User user2 = new User("admin", "$2a$10$uuY8JpD4EwyvBh0GKDn1u.55msv3CPSIFWjH4pQo0YiGD6E8/8zr6",
					"admin@mail.com", "ADMIN");
			User user3 = new User("elisa", "$2a$10$t2mA/dDxeW6KWKVvV1zg/e/bKfLJ1V64zxo6lBXWwBXLhhxyNA4sG",
					"elisa@mail.com", "ADMIN");
			userRepository.save(user1);
			userRepository.save(user2);
			userRepository.save(user3);

		};
	}

}
