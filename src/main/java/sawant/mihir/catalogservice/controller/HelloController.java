package sawant.mihir.catalogservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sawant.mihir.catalogservice.config.CatalogProperties;

@RestController
public class HelloController {

    private CatalogProperties catalogProperties;

    public HelloController(CatalogProperties catalogProperties) {
        this.catalogProperties = catalogProperties;
    }

    @GetMapping("/")
    public String hello(){
        return catalogProperties.getMessage();
    }
}
