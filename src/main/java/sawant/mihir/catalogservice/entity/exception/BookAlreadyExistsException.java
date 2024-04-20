package sawant.mihir.catalogservice.entity.exception;

public class BookAlreadyExistsException extends RuntimeException {
    public BookAlreadyExistsException(String isbn) {
        super("A book with ISBN: "+isbn+" already exists !");
    }
}