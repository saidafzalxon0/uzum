package uz.java.uzum.service;

import uz.java.uzum.dto.CommentDto;
import uz.java.uzum.dto.ResponseDto;

import java.util.List;

public interface CommentService {
    ResponseDto<List<CommentDto>> viewAll(Integer id);
    ResponseDto<CommentDto> addComment(Integer id, String comment);
    ResponseDto<CommentDto> removeComment(Integer id);
}
