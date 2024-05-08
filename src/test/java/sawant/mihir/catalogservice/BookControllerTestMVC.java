package sawant.mihir.catalogservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import sawant.mihir.catalogservice.controller.BookController;
import sawant.mihir.catalogservice.entity.Book;
import sawant.mihir.catalogservice.entity.exception.BookNotFoundException;
import sawant.mihir.catalogservice.service.BookService;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class).
public class BookControllerTestMVC {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;


    @Test
    public void check404Error() throws Exception {
       var fakeIsbn = "1938289291";
       given(bookService.getBook(fakeIsbn))
               .willThrow(BookNotFoundException.class);

      mockMvc.perform(get("/books/"+fakeIsbn))
              .andExpect(status().isNotFound());

    }

    @Test
    public void checkAuthorValidaton() throws Exception {
        var book =  Book.of("1234567890", "test", "", 100);

        mockMvc.perform(put("/books"))
                .andExpect(status().is4xxClientError());
    }
}
