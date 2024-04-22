package sawant.mihir.catalogservice.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

public record Book(

        @Id
        Long id,

        @NotBlank(message = "The ISBN code cannot be blank")
                @Pattern(
                        regexp =  "^([0-9]{10}|[0-9]{13})$",
                        message = "ISBN code must follow proper format"
                )
        String isbn,

        @NotBlank(message = "Title cannot be blank")

        String title,

        @NotBlank(message = "Author cannot be blank")
        String author,

        @NotNull(message = "Price cannot be null")
                @Positive(message = "Price must always be greater than zero")
        double price,

        @Version
        int version
) {
    public static Book of(
            String isbn, String title, String author ,double price
    ){
        return new Book(null, isbn, title, author, price, 0);
    }
}
