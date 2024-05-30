package sawant.mihir.catalogservice;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import sawant.mihir.catalogservice.entity.Book;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles("")
class CatalogServiceApplicationTests {


    @Autowired
    private WebTestClient webTestClient;
    @Test
    void addingANewBook() {
        var result =  Book.of("1234567890", "test", "abc", 900.20, "llp");

        webTestClient
                .post()
                .uri("/books")
                .bodyValue(result)
                .exchange()
                .expectStatus().isCreated();


    }

    @Test
    void gettingBook(){
        webTestClient
                .get()
                .uri("/books")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Book.class)
                .hasSize(1);
    }

//    @Test
    void updateBook() throws JSONException {
        var update = new JSONObject();
        update.put("author", "abc");
        update.put("title", "test 2");
        update.put("price", 2000);

        webTestClient
                .put()
                .uri("/books/1234567890")
                .bodyValue(update)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Book.class)
                .value(b -> {
                    assertThat(b.price()).isEqualTo(2000);
                    assertThat(b.isbn()).isEqualTo("1234567890");
                    assertThat(b.title()).isEqualTo("test 2");
                });
    }


    @Test
    void deleteBook(){
        webTestClient
                .delete()
                .uri("/books/1234567890")
                .exchange()
                .expectStatus().isNoContent();
        webTestClient
                .get()
                .uri("/books/1234567890")
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(String.class)
                .value(s -> assertThat(s).isEqualTo("Book with ISBN: 1234567890 not found !"));
    }

}
