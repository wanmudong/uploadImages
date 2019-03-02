package top.wanmudong.images.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import top.wanmudong.images.entity.Images;
import top.wanmudong.images.mapper.ImagesMapper;
import top.wanmudong.images.service.IImagesService;

import org.springframework.stereotype.Service;
import top.wanmudong.images.utils.MyPageInfo;
import top.wanmudong.images.utils.timeUtil;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenjiehao
 * @since 2019-02-27
 */
@Service
public class ImagesServiceImpl extends ServiceImpl<ImagesMapper, Images> implements IImagesService {

//    String filePath = "D:/images/";
    @Value("${file.uploadAddr}")
    String filePath ;

    private ImagesMapper ImagesMapper;
    @Autowired(required=true)
    protected ImagesServiceImpl(ImagesMapper ImagesMapper) {

        this.ImagesMapper = ImagesMapper;
    }

    private static final Logger logger = LoggerFactory.getLogger(ImagesServiceImpl.class);


    @Override
    public boolean uploadImages(MultipartFile file, String username) {

        String orginalFilename = file.getOriginalFilename();
        String fileType = orginalFilename.substring(orginalFilename.lastIndexOf(".") + 1);
        String filename = UUID.randomUUID().toString().replace("-", "")+"."+fileType;
        String onefilepath =  username+"/"+filename;

        logger.info("用户："+username+"--上传文件："+filename);

        saveImages(file,onefilepath);

        Images image = new Images();
        image.setAddr(onefilepath);
        image.setUsername(username);
        image.setCreateDate(timeUtil.getSecondDateTimeNow());

        baseMapper.saveImages(image);

        return true;
    }

    @Override
    public boolean deleteImages(int id) {

        String addr = baseMapper.selectById(id).getAddr();
        String onefilepath = filePath + addr;

        deleteOneImage(onefilepath);

        baseMapper.deleteById(id);


        return true;
    }

    @Override
    public boolean putImages(int id, MultipartFile file) {

        String addr = baseMapper.selectById(id).getAddr();

        saveImages(file,addr);

        return true;
    }

    @Override
    public MyPageInfo<Images> getImages(String username, String start_date, String end_date) {

        PageInfo<Images> pageInfo = new PageInfo<Images>(baseMapper.getImages(username,start_date,end_date));

        return new MyPageInfo<>(pageInfo);
    }

//    private boolean putOneImages(String onefilepath, MultipartFile file) {
//
//        File file = new File(onefilepath);
//
//        return true;
//    }

    private boolean deleteOneImage(String onefilepath) {

        File file = new File(onefilepath);

        if(file.delete()){
            logger.info("文件："+onefilepath+"删除成功");
        }else {
            logger.error("文件："+onefilepath+"删除失败");
        }

        return true;
    }

    private boolean saveImages(MultipartFile file, String onefilepath) {



        File dest = new File(filePath + onefilepath);
        try {
            File fileParent = dest.getParentFile();
            if(!fileParent.exists()){
                fileParent.mkdirs();
            }
            file.transferTo(dest);
            logger.info("文件上传成功");
        } catch (IOException e) {
            logger.error(e.toString(),e);
        }

        return true;
    }
}
