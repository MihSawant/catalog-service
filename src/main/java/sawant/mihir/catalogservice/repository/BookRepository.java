package sawant.mihir.catalogservice.repository;

import sawant.mihir.catalogservice.entity.Book;

import java.util.Optional;

public interface BookRepository {
     Iterable<Book> findAll();
     Optional<Book> findByIsbn(String isbn);
     boolean existsByIsbn(String isbn);
     Book save(Book book);

     void deleteByIsbn(String isbn);
}
