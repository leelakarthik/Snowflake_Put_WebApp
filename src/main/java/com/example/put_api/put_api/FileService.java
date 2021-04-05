package com.example.put_api.put_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class FileService {

    @Value("${app.upload.dir:${user.home}}")
    public String uploadDir;
    @Autowired
    SnowConnection snowConnection;
    @Autowired
    Put_into_stage pis;

    public void uploadFile(MultipartFile file, String stage_name) {

        try {
            Path copyLocation = Paths
                    .get(uploadDir + File.separator + StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename())));
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
            pis.Put_file(uploadDir, Objects.requireNonNull(file.getOriginalFilename()), stage_name, snowConnection);
            Files.deleteIfExists(Paths.get(uploadDir + "\\" + file.getOriginalFilename()));
        } catch (Exception e) {
            e.printStackTrace();
            throw new FileStorageException("Could not store file " + file.getOriginalFilename()
                    + ". Please try again!");
        }

    }
}