package sawant.mihir.catalogservice;

import jakarta.validation.Validation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import sawant.mihir.catalogservice.entity.Book;

import static org.assertj.core.api.Assertions.assertThat;

public class BookValidationTests {

    private static Validator validator;
    @BeforeAll
    public static void setup(){
        var vf = Validation.buildDefaultValidatorFactory();
        validator = vf.getValidator();
    }

    @Test
    public void rightBook(){
        var book =  Book.of("1234567899", "My Book 1", "Tom",1991.12, "pp");
        var result = validator.validate(book);
        assertThat(result).isEmpty();
    }

    @Test
    public void wrongBookAuthor(){
        var book =  Book.of("1111111111", "test 1", "", 222, "llp");
        var result = validator.validate(book);
        assertThat(result).hasSize(1);
        assertThat(result.iterator().next().getMessage()).isEqualTo("Author cannot be blank");}
}
