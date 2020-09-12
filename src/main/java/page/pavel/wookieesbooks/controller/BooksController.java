package page.pavel.wookieesbooks.controller;

import page.pavel.wookieesbooks.config.Logging;
import page.pavel.wookieesbooks.model.Book;
import page.pavel.wookieesbooks.model.User;
import page.pavel.wookieesbooks.repository.BooksRepository;
import page.pavel.wookieesbooks.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for Books
 */
@RestController
@RequestMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
public class BooksController {

    private final Logging logger = new Logging(LogManager.getLogger(this.getClass()));

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Get all or filtered books
     *
     * @link http://localhost:8080/books
     * @link http://localhost:8080/books?id=1,3
     * @link http://localhost:8080/books?userid=1,2
     * @link http://localhost:8080/books?title=book_2,book_3
     * @link http://localhost:8080/books?userid=1,2&title=book_2,book_3
     */
    @GetMapping(value = "")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Book> getFilteredBooks(@RequestParam(value = "id", required = false) Optional<List<Long>> ids
            , @RequestParam(value = "userid", required = false) Optional<List<Long>> userIds
            , @RequestParam(value = "username", required = false) Optional<String> username
            , @RequestParam(value = "title", required = false) Optional<List<String>> titles
            , HttpServletRequest request) {

        List<Long> idsList = ids.orElseGet(ArrayList::new);
        List<Long> userIdsList = userIds.orElseGet(ArrayList::new);
        String usernameParam = username.orElseGet(String::new);
        List<String> titlesList = titles.orElseGet(ArrayList::new);
        List<Book> books = new ArrayList<>();

        logger.requestStart(request);

        if (request.getParameterMap().isEmpty()) {
            books = booksRepository.findAll();
        } else if (request.getParameterMap().size() == 1) {
            if (request.getParameterMap().containsKey("id")) {
                books = booksRepository.findByBookIdIn(idsList);
            }
            if (request.getParameterMap().containsKey("userid")) {
                books = booksRepository.findByUser_IdIn(userIdsList);
            }
            if (request.getParameterMap().containsKey("username")) {
                books = booksRepository.findByUser_Username(usernameParam);
            }
            if (request.getParameterMap().containsKey("title")) {
                books = booksRepository.findByTitleIn(titlesList);
            }
        } else if (request.getParameterMap().containsKey("userid") && request.getParameterMap().containsKey("title")) {
            books = booksRepository.findByUserIdInAndTitleIn(userIdsList, titlesList);
        }

        logger.requestStop(books);
        return books;
    }

    /**
     * Publish a Book
     *
     * @link http://localhost:8080/books
     */
    @PostMapping(value = "")
    @PutMapping(value = "")
    @ResponseBody
    public Book publishBook(@RequestBody Book book, HttpServletRequest request) {

        logger.requestStart(request);

        User user = userRepository.findByUsername(request.getRemoteUser());
        book.setUser(user);
        booksRepository.save(book);

        logger.requestStop(book);
        return book;
    }

    /**
     * Unpublish a Book
     *
     * @link http://localhost:8080/books
     */
    @DeleteMapping(value = "")
    @Transactional
    public void unpublishBook(@RequestParam(value = "bookId") Long bookId, HttpServletRequest request) {

        logger.requestStart(request);

        booksRepository.delete(booksRepository.findByBookId(bookId));

        logger.requestStop(bookId);
    }
}
