package com.api.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.api.exception.instruktur.InstrukturExceptionNotFound;

public class FileUploadUtil {

    public static String saveFile(String filename, MultipartFile multipartFile) {
        Path uploadDirectory = Paths.get("fotos");

        String fileCode = RandomStringUtils.randomAlphanumeric(8);

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadDirectory.resolve(fileCode + "-" + filename);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new InstrukturExceptionNotFound("Error saving uploaded file:" + filename, ioe);
        }

        return fileCode;
    }
}
