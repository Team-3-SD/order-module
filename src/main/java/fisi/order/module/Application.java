package fisi.order.module;

import fisi.order.module.application.resource.IUploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Application extends SpringBootServletInitializer  {

    @Autowired
    IUploadFileService uploadFileService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

//    public void run(String... args) throws Exception {
//        uploadFileService.init();
//    }
}
