package sawant.mihir.catalogservice.data;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import sawant.mihir.catalogservice.entity.Book;
import sawant.mihir.catalogservice.repository.BookRepository;

import java.util.List;

@Component
@Profile("dataload")
public class DataLoader {

    private final BookRepository bookRepository;

    public DataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadData(){
        bookRepository.deleteAll();
        List<Book> books = List.of(
                Book.of("1234567890", "Never Lie: An addictive psychological thriller",
                        "Freida McFadden", 3.99),
                Book.of("1234567891", "The Women: A Novel",
                        "Kristin Hannah", 14.99),
                Book.of("1234567892", "Playing It My Way: My Autobiography",
                        "Sachin Tendulkar", 2.99),
                Book.of("1234567893", "Atomic Habits: An Easy and Proven way to build " +
                                "good habits and break bad ones",
                        "James Clear", 13.99)

        );
        bookRepository.saveAll(books);
        bookRepository.findAll().forEach(System.out::println);
    }
}
