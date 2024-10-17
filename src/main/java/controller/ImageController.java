package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
public class ImageController {

    @Autowired
    private ServletContext servletContext;

    @GetMapping("/image")
    public void showImage(HttpServletResponse response, @RequestParam String imagePath) {
        // ServletContext를 통해 실제 경로를 가져옴
        String realPath = servletContext.getRealPath(imagePath);

        // 이미지 파일 객체 생성
        File imageFile = new File(realPath);
        if (imageFile.exists()) {
            response.setContentType("image/jpeg");  // 이미지의 MIME 타입 설정
            try (FileInputStream fis = new FileInputStream(imageFile);
                 ServletOutputStream out = response.getOutputStream()) {

                byte[] buffer = new byte[1024];
                int length;
                while ((length = fis.read(buffer)) != -1) {
                    out.write(buffer, 0, length);
                }
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
