package top.wanmudong.images.service;

import com.baomidou.mybatisplus.service.IService;
import org.springframework.web.multipart.MultipartFile;
import top.wanmudong.images.entity.Images;
import top.wanmudong.images.utils.MyPageInfo;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenjiehao
 * @since 2019-02-27
 */
public interface IImagesService extends IService<Images> {

    boolean uploadImages(MultipartFile file, String username);

    boolean deleteImages(int id);

    boolean putImages(int id, MultipartFile file);

    MyPageInfo<Images> getImages(String username, String start_date, String end_date);
}
