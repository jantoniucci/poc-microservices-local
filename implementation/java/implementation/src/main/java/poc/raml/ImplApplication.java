package poc.raml;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
public class ImplApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ImplApplication.class);
    }

    public static void main(String[] args) {
        new ImplApplication()
                .configure(new SpringApplicationBuilder(ImplApplication.class))
                .run(args);
    }

}