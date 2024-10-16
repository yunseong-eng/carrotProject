package dao;

import dto.ImageDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ImageDAO {
    // 이미지 업로드
    void insertImage(ImageDTO imageDTO);

    // 게시글의 이미지 목록 조회
    List<ImageDTO> getImageList(int boardId);

    // 이미지 삭제
    void deleteImage(int imageId);

    // 특정 이미지 조회
    ImageDTO getImage(int imageId);
}
