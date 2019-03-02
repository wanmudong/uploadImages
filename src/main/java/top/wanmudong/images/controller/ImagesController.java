package top.wanmudong.images.controller;


import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import top.wanmudong.images.entity.Images;
import top.wanmudong.images.entity.Result;
import top.wanmudong.images.entity.Upload;
import top.wanmudong.images.service.IImagesService;
import top.wanmudong.images.utils.MyPageInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chenjiehao
 * @since 2019-02-27
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/upload/images")
@Slf4j
@Api(tags = "图片上传管理")
public class ImagesController {

    private final Logger logger = LoggerFactory.getLogger(ImagesController.class);

    @Autowired(required = true)
    private IImagesService iImagesService;


    @PostMapping("")
    @ApiOperation("上传图片")
    public Result uploadImages(@Valid  Upload upload){

        boolean success = iImagesService.uploadImages(upload.getFile(),upload.getUsername());


        return Result.OK();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除图片")
    public Result deleteImages(@PathVariable int id){

        iImagesService.deleteImages(id);

        return Result.OK();
    }

    @PutMapping("/{id}")
    @ApiOperation("更新图片")
    public  Result putImages(@PathVariable int id, MultipartFile file){

        if (file== null ||file.isEmpty()){
            return Result.Error("上传的图片不能为空");
        }

        iImagesService.putImages(id,file);

        return Result.OK();
    }

    @GetMapping("")
    @ApiOperation("批量获取图片")
    public Result getImages(@RequestParam(defaultValue = "1") int pageNo,
                            @RequestParam(defaultValue = "10")int pageSize,
                            String username,
                            String start_date,
                            String end_date){

        PageHelper.startPage(pageNo,pageSize);
        MyPageInfo<Images> myPageInfo = iImagesService.getImages(username,start_date,end_date);

        return Result.OK().put("data",myPageInfo);
    }

    @GetMapping("up")
    public Result up(){
        return  Result.OK();
    }
}
