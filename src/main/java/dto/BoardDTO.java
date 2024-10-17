package dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class BoardDTO {
    private int boardId;            // 글 ID
    private String userId;          // 작성자 ID
    private String title;           // 제목
    private String content;         // 내용
    private String category;        // 카테고리 (의류, 신발)
    private String postType;        // 게시글 유형 (판매, 구매)
    private LocalDateTime createTime; // 작성일
    private int views;              // 조회수
    private String shippingFee;     // 배송비 (무료, 유료)
    private boolean includes;       // 구성품 여부 (true: 있음, false: 없음)
    private String imagePath;       // 이미지 경로
    private boolean isNotice;       // 공지사항 여부 (true: 공지사항, false: 일반 글)
	
}