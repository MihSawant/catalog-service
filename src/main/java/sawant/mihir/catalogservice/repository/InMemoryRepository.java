package sawant.mihir.catalogservice.repository;

import org.springframework.stereotype.Repository;
import sawant.mihir.catalogservice.entity.Book;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryRepository  implements BookRepository{
    private static Map<String, Book> db = new ConcurrentHashMap<>();
    @Override
    public Iterable<Book> findAll() {
        return db.values();
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return existsByIsbn(isbn) ? Optional.of(db.get(isbn)) : Optional.empty();
    }

    @Override
    public boolean existsByIsbn(String isbn) {
        return db.get(isbn) != null;
    }

    @Override
    public Book save(Book book) {
        return db.put(book.isbn(), book);
    }

    @Override
    public void deleteByIsbn(String isbn) {
        db.remove(isbn);
    }
}
