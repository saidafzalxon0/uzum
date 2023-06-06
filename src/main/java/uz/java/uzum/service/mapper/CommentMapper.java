package uz.java.uzum.service.mapper;

import org.mapstruct.Mapper;
import uz.java.uzum.dto.CommentDto;
import uz.java.uzum.entity.Comment;

@Mapper(componentModel = "spring")
public abstract class CommentMapper implements CommonMapper<CommentDto, Comment>{
    @Override
    public abstract CommentDto toDto(Comment comment);

    @Override
    public abstract Comment toEntity(CommentDto commentDto);
}
