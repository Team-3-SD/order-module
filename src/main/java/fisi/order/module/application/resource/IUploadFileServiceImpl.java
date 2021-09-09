package fisi.order.module.application.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class IUploadFileServiceImpl implements IUploadFileService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final static String UPLOADS_FOLDER = "home/team3/uploads";

    @Override
    public String copy(MultipartFile file) throws IOException {
        String uniqueFilename = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path rootPath = getPath(uniqueFilename);

        log.info("rootPath: " + rootPath);

        Files.copy(file.getInputStream(), rootPath);

        return uniqueFilename;
    }

    @Override
    public void init() throws IOException {
        Files.createDirectory(Paths.get(UPLOADS_FOLDER));
    }

    @Override
    public Resource load(String filename) throws MalformedURLException {
        Path pathArchivo = getPath(filename);
        log.info("pathArchivo: " + pathArchivo);
        Resource recurso;

        recurso = new UrlResource(pathArchivo.toUri());
        if (!recurso.exists() || !recurso.isReadable()) {
            throw new RuntimeException("Error no se puede cargar el archivo: " + pathArchivo);
        }
        return recurso;
    }

    @Override
    public boolean delete(String filename) {
        Path rootPath = getPath(filename);
        File archive = rootPath.toFile();

        if (archive.delete()) {
            return true;
        }
        return false;
    }

    public Path getPath(String filename) {
        return Paths.get(UPLOADS_FOLDER).resolve(filename).toAbsolutePath();
    }
}
