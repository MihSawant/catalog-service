package sawant.mihir.catalogservice.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import org.springframework.context.annotation.Primary;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import java.time.Instant;

public record Book(

        @Id
        Long id,

        @CreatedDate
        Instant createdDate,

        @LastModifiedDate
        Instant lastModifiedDate,

        @NotBlank(message = "The ISBN code cannot be blank")
                @Pattern(
                        regexp =  "^([0-9]{10}|[0-9]{13})$",
                        message = "ISBN code must follow proper format"
                )
        String isbn,

        String publisher,

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
            String isbn, String title, String author ,double price, String publisher
    ){
        return new Book(null, null, null, isbn, publisher, title, author, price, 0);
    }
}
