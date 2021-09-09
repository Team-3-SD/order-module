package fisi.order.module.application.resource;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;

public interface IUploadFileService {

    String copy(MultipartFile file) throws IOException;

    void init() throws IOException;

    Resource load(String filename) throws MalformedURLException;

    boolean delete(String filename);

}
