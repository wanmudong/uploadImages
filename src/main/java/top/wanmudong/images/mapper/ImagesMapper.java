package top.wanmudong.images.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.wanmudong.images.entity.Images;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chenjiehao
 * @since 2019-02-27
 */
@Mapper
public interface ImagesMapper extends BaseMapper<Images> {

    void saveImages(@Param("image") Images image);

    List<Images> getImages(@Param("username") String username, @Param("start_date") String start_date, @Param("end_date") String end_date);
}
