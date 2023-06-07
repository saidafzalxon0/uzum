package uz.java.uzum.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.java.uzum.dto.ResponseDto;
import uz.java.uzum.dto.UploadImageDto;
import uz.java.uzum.entity.Image;
import uz.java.uzum.entity.ImageQuality;
import uz.java.uzum.repository.ImageQualityRepository;
import uz.java.uzum.repository.ImageRepository;
import uz.java.uzum.service.ImageService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static uz.java.uzum.service.appStatus.AppStatusCodes.*;
import static uz.java.uzum.service.appStatus.AppStatusMessages.*;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageQualityRepository imageQualityRepository;
    private final ImageRepository imageRepository;
    @Override
    public ResponseDto<Integer> fileUpload(UploadImageDto file) {
        ImageQuality imageQuality = new ImageQuality();
        imageQuality.setImageUrl(saveFile(file.getImage()));

        imageQualityRepository.save(imageQuality);
        return ResponseDto.<Integer>builder()
                .message(OK)
                .code(OK_CODE)
                .success(true)
                .data(imageQuality.getId())
                .build();

    }

    @Override
    public ResponseDto<List<byte[]>> getFileById(Integer id, String size) throws IOException {
        if (id == null){
            return ResponseDto.<List<byte[]>>builder()
                    .message(NULL_VALUE)
                    .code(VALIDATION_ERROR_CODE)
                    .build();
        }

        Optional<Image> image = imageRepository.findById(id);
        if (image.isEmpty()){
            return ResponseDto.<List<byte[]>>builder()
                    .code(NOT_FOUND_ERROR_CODE)
                    .message(NOT_FOUND)
                    .build();
        }
        List<byte[]> imagesList = new ArrayList<>();

        for(ImageQuality imageQuality: image.get().getImageQualitys()){
            imagesList.add(getImage(imageQuality.getImageUrl()));
        }

        return ResponseDto.<List<byte[]>>builder()
                .message(OK)
                .code(OK_CODE)
                .success(true)
                .data(imagesList)
                .build();
    }

    public String filePath(String folder, String ext) {
        LocalDate localDate = LocalDate.now();
        String path = localDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        File file = new File(folder + "/" + path);
        if (!file.exists()) {
            file.mkdirs();
        }

        String uuid = UUID.randomUUID().toString();
        return file.getPath() + "\\" + uuid + ext;
    }

    public String saveFile(MultipartFile image) {
        String ext = image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf("."));
        try {
            String filePath;
            Files.copy(image.getInputStream(), Path.of(filePath = filePath("upload", ext)));
            return filePath;
        } catch (IOException e) {
            return null;
        }
    }
    public byte[] getImage(String imageUrl){
        byte[] file = new byte[0];
        try {
            file = new FileInputStream(imageUrl).readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file;
    }
}
