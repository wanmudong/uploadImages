package top.wanmudong.images.entity;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author wanmudong
 * @date 16:06 2019/3/1
 */
@Data
public class Upload {

    @NotNull(message = "上传的图片不能为空")
    private MultipartFile file;

    @NotBlank(message = "上传者不可为空")
    private String username;

}
