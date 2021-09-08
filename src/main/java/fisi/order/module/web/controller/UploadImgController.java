package fisi.order.module.web.controller;

import fisi.order.module.application.resource.IUploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;

@RestController
@RequestMapping({"/api/v1/file"})
public class UploadImgController {

    @Autowired
    private IUploadFileService uploadFileService;

    @GetMapping(value = "/output/{filename:.+}")
    public ResponseEntity<Resource> verArchivo(@PathVariable String filename) {
        Resource recurso = null;
        try {
            recurso = uploadFileService.load(filename);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).body(recurso);
    }
}
