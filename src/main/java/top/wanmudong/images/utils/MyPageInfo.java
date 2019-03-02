package top.wanmudong.images.utils;

import lombok.Data;
import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;

/**
 * @author wanmudong
 * @date 16:34 2019/3/1
 */
@Data
public class MyPageInfo<T> implements Serializable {


    /**
     * 总记录数
     */
    private int total;
    /**
     * 每页条数
     */
    private int size;
    /**
     * 总页数
     */
    private int pages;
    /**
     * 当前页数
     */
    private int page;
    /**
     * 具体数据
     */
    private List<T> list;

    public MyPageInfo(PageInfo<T> pageInfo) {
        this.size = pageInfo.getSize();
        this.total = (int) pageInfo.getTotal();
        this.pages =pageInfo.getPages();
        this.page = pageInfo.getPageNum();
        this.list = pageInfo.getList();
    }
    public MyPageInfo(PageInfo<T> pageInfo,List list) {
        this.size = pageInfo.getSize();
        this.total = (int) pageInfo.getTotal();
        this.pages =pageInfo.getPages();
        this.page = pageInfo.getPageNum();
        this.list = list;
    }

}
