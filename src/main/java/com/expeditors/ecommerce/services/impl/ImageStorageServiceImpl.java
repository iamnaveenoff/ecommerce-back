package com.expeditors.ecommerce.services.impl;

import com.expeditors.ecommerce.services.ImageStorageService;
import io.jsonwebtoken.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageStorageServiceImpl implements ImageStorageService {

    @Value("${image.product.upload.dir}")
    private String imageProductUploadDir;

    @Value("${image.category.upload.dir}")
    private String imageCategoryUploadDir;


    public String storeProductImage(MultipartFile file, Long productId) throws IOException, java.io.IOException {
        String fileExtension = getFileExtension(file.getOriginalFilename());
        String fileName = productId + fileExtension;

        Path filePath = Paths.get(imageProductUploadDir, fileName);

        Files.createDirectories(filePath.getParent());
        file.transferTo(filePath);

        return "images/product/" + fileName;
    }

    public String storeCategoryImage(MultipartFile file) throws IOException, java.io.IOException {
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(imageCategoryUploadDir, fileName);

        Files.createDirectories(filePath.getParent());
        file.transferTo(filePath);

        return "images/category/" + fileName;
    }

    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex);
    }

}
