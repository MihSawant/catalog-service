package sawant.mihir.catalogservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.test.context.ActiveProfiles;
import sawant.mihir.catalogservice.config.DataConfig;
import sawant.mihir.catalogservice.entity.Book;
import sawant.mihir.catalogservice.repository.BookRepository;
import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

@DataJdbcTest
@Import(DataConfig.class)
@AutoConfigureTestDatabase(
        replace = AutoConfigureTestDatabase.Replace.NONE
)
@ActiveProfiles("integration")
public class BookRepositoryJdbcTests {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private JdbcAggregateTemplate template;

    @Test
    void newBookAndVerify(){
        var isbn = "9999999999";
        var book = Book.of(isbn, "test", "abc", 300);
        template.insert(book);
        Optional<Book> result = bookRepository.findBookByIsbn(isbn);
        assertThat(result).isPresent();
        assertThat(result.get().author()).isEqualTo("abc");

    }
}
