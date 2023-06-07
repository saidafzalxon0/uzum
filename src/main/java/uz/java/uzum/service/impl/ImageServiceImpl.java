package uz.java.uzum.service.impl;

import org.springframework.stereotype.Service;
import uz.java.uzum.dto.ResponseDto;
import uz.java.uzum.dto.UploadImageDto;
import uz.java.uzum.service.ImageService;

import java.io.IOException;
@Service
public class ImageServiceImpl implements ImageService {
    @Override
    public ResponseDto<Integer> fileUpload(UploadImageDto file) {
        return null;
    }

    @Override
    public ResponseDto<byte[]> getFileById(Integer id, String size) throws IOException {
        return null;
    }
}
