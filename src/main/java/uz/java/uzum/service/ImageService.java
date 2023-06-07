package uz.java.uzum.service;

import uz.java.uzum.dto.ResponseDto;
import uz.java.uzum.dto.UploadImageDto;

import java.io.IOException;

public interface ImageService {
    ResponseDto<Integer> fileUpload(UploadImageDto file);
    ResponseDto<byte[]> getFileById(Integer id,String size) throws IOException;
}
