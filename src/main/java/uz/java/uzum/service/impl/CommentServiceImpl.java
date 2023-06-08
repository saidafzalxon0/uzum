package uz.java.uzum.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.java.uzum.dto.CommentDto;
import uz.java.uzum.dto.ResponseDto;
import uz.java.uzum.service.CommentService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    @Override
    public ResponseDto<List<CommentDto>> viewAll(Integer id) {
        return null;
    }

    @Override
    public ResponseDto<CommentDto> addComment(Integer id, String comment) {
        return null;
    }

    @Override
    public ResponseDto<CommentDto> removeComment(Integer id) {
        return null;
    }
}
