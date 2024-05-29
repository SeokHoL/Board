package kr.ac.kopo.board.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private String resourcePath = "/upload/**"; //view 에서 접근할 경로 2. /upload/**이걸로 접근하겠다~
    private String savePath ="file:///C:/springboot_img/"; //실제 파일 저장 경로  1. file:///C:/springboot_img/ 여기 경로에있는 것을

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler(resourcePath)
                .addResourceLocations(savePath);
    }
}
