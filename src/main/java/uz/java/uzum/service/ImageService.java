package uz.java.uzum.service;

import uz.java.uzum.dto.ImageQualityDto;
import uz.java.uzum.dto.ResponseDto;
import uz.java.uzum.dto.UploadImageDto;

import java.io.IOException;
import java.util.List;

public interface ImageService {
    ResponseDto<Integer> fileUpload(UploadImageDto image);
    ResponseDto<List<byte[]>> getFileById(Integer id, String size) throws IOException;
}
