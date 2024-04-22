package sawant.mihir.catalogservice.service;

import org.springframework.stereotype.Service;
import sawant.mihir.catalogservice.entity.Book;
import sawant.mihir.catalogservice.entity.exception.BookAlreadyExistsException;
import sawant.mihir.catalogservice.entity.exception.BookNotFoundException;
import sawant.mihir.catalogservice.repository.BookRepository;

import java.util.Optional;

@Service
public class BookService {
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public Iterable<Book> listAllBooks(){
        return bookRepository.findAll();
    }

    public Book addBook(Book book){
        if(bookRepository.existsByIsbn(book.isbn())){
            throw new BookAlreadyExistsException(book.isbn());
        }
        return bookRepository.save(book);
    }

    public Book getBook(String isbn){
        return bookRepository.findByIsbn(isbn).orElseThrow(() -> new BookNotFoundException(isbn));
    }

    public Book editBook(String isbn, Book book){
        var originalBook = bookRepository.findByIsbn(isbn);
        if(originalBook.isPresent()){
            Book newBook = new Book(originalBook.get().id(), originalBook.get().isbn(), book.title(),
                    book.author(), book.price(), originalBook.get().version());
            bookRepository.save(newBook);
            return bookRepository.findByIsbn(isbn).get();
        } else{
            return addBook(book);
        }

    }

    public void removeBook(String isbn){
        bookRepository.deleteByIsbn(isbn);
    }
}
