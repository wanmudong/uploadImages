package top.wanmudong.images.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author chenjiehao
 * @since 2019-02-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Images implements Serializable {

    private static final long serialVersionUID = 1L;


    @TableId
    private int id;

    /**
     * 上传文件的用户账号
     */
    private String username;

    /**
     * 上传文件的文件相对地址
     */
    private String addr;

    /**
     * 上传时间
     */
    private String createDate;

    /**
     * 修改时间
     */
    private LocalDateTime modifiedDate;


}
