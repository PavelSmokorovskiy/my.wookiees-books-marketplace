package page.pavel.wookieesbooks;

import page.pavel.wookieesbooks.model.Book;
import page.pavel.wookieesbooks.model.User;
import page.pavel.wookieesbooks.repository.BooksRepository;
import page.pavel.wookieesbooks.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.transaction.Transactional;

@Configuration
@EnableJpaRepositories
@EnableJpaAuditing
@SpringBootApplication
public class WookieesBooksApplication implements CommandLineRunner {

    private static final Logger logger = LogManager.getLogger(WookieesBooksApplication.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BooksRepository booksRepository;

    public static void main(String[] args) {
        logger.info("Start Log4j2 logger");
        SpringApplication.run(WookieesBooksApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) {

        //Bcrypted String "password"
        User user1 = new User("username_1"
                ,"$2a$10$M1fQOPWvy1VQVrEkGIHO/.mq/rW5goOV1tbC0b4OB7xxer5LvJxGS"
                , "email_1");
        User user2 = new User("username_2"
                ,"$2a$10$M1fQOPWvy1VQVrEkGIHO/.mq/rW5goOV1tbC0b4OB7xxer5LvJxGS"
                , "email_2");
        userRepository.save(user1);
        userRepository.save(user2);

        Book book1 = new Book(user1
                , "book_1"
                , "description_1"
                , "imageURL_1"
                , 10.5);
        Book book2 = new Book(user2
                , "book_2"
                , "description_2"
                , "imageURL_2"
                , 20.88);
        Book book3 = new Book(user1
                , "book_3"
                , "description_3"
                , "imageURL_3"
                , 12.34);
        booksRepository.save(book1);
        booksRepository.save(book2);
        booksRepository.save(book3);
    }
}
