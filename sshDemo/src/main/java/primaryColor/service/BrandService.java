package primaryColor.service;

import primaryColor.pojo.Brand;
import primaryColor.pojo.PageBean;

import java.util.List;

/**
 * @author PrimaryColor
 * @date 2023/2/1
 * @apiNote
 */
public interface BrandService {
    /**
     * 查询所有
     * @return
     */
    List<Brand> selectAll();
    /**
     * 添加
     * @param brand
     */
    void add(Brand brand);
    /**
     * 根据id查询
     * @param id
     * @return
     */
    Brand selectById(int id);
    /**
     * 修改数据
     * @param brand
     */
    void update(Brand brand);
    /**
     * 删除数据
     * @param brand
     */
    void delete(Brand brand);

    /**
     * 分页查询
     * @param currentPage 当前页码
     * @param pageSize 条目数
     * @return
     */
    PageBean<Brand> selectByPage(int currentPage, int pageSize);

    /**
     * 分页条件查询
     * @param currentPage
     * @param pageSize
     * @param brand
     * @return
     */
    PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand);
}
