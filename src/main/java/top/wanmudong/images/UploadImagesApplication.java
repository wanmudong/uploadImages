package top.wanmudong.images;

import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("top.wanmudong.images.mapper")
@Configuration
@EnableSwagger2
public class UploadImagesApplication {

    public static void main(String[] args) {

        SpringApplication.run(UploadImagesApplication.class, args);
    }



}
