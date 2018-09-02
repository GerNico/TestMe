package com.test.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
@RequiredArgsConstructor
public class FileController {
    @PostMapping(value = "rest/file/uploadFile")
    public boolean uploadFile(@RequestParam("file") MultipartFile file,
                              @RequestParam("comment") String comment,
                              @RequestParam("id") Long id,
                              @RequestParam("managerId") Long managerId
                              ) throws IOException {
        Path path = Paths.get("/home/nicolas/"+file.getOriginalFilename());
        Files.write(path,file.getBytes());
        return true;
    }
}
