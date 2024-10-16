package dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageDTO {
    private int imageId;                // 이미지 ID
    private int boardId;                // 게시글 ID
    private String imageName;           // 이미지 이름
    private String imageContent;        // 이미지 설명
    private String imageFileName;       // 저장된 파일명
    private String imageOriginalName;   // 원본 파일명
}
