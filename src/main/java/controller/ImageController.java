package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import service.ObjectStorageService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ImageController {

    @Autowired
    private ObjectStorageService objectStorageService;  // 네이버 클라우드 서비스 주입

    @GetMapping("/image")
    public void showImage(HttpServletResponse response, @RequestParam String imageUrl) throws IOException {
        if (imageUrl != null && !imageUrl.isEmpty()) {
            response.sendRedirect(imageUrl); // 클라우드 URL로 리다이렉트
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 파라미터 누락 시 에러 처리
        }
    }

}
