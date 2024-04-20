package sawant.mihir.catalogservice.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sawant.mihir.catalogservice.entity.Book;
import sawant.mihir.catalogservice.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public Iterable<Book> getAllBooks(){
        return bookService.listAllBooks();
    }

    @GetMapping("{isbn}")
    public Book findBook(@PathVariable(name = "isbn") String isbn){
        return bookService.getBook(isbn);
    }

    @PutMapping("{isbn}")
    public Book updateBook(@PathVariable(name = "isbn") String isbn,
                           @Valid @RequestBody Book book){
        return bookService.editBook(isbn, book);
    }

    @DeleteMapping("{isbn}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable(name = "isbn") String isbn){
        bookService.removeBook(isbn);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book saveBook(@Valid @RequestBody Book book){
        return bookService.addBook(book);
    }
}
