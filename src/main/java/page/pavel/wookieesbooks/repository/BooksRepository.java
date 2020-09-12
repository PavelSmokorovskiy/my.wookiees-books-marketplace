package page.pavel.wookieesbooks.repository;

import page.pavel.wookieesbooks.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Books repository
 * Repository can be used to delegate CRUD operations against the data source: http://goo.gl/P1J8QH
 */
@Repository
public interface BooksRepository extends JpaRepository<Book, Long> {

    Book findByBookId(Long bookId);

    List<Book> findByBookIdIn(List<Long> bookIds);

    List<Book> findByUser_IdIn(List<Long> ids);

    List<Book> findByUser_Username(String usernames);

    List<Book> findByTitleIn(List<String> titles);

    List<Book> findByUserIdInAndTitleIn(List<Long> ids, List<String> titles);

}
