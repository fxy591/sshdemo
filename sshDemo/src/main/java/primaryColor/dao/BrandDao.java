package primaryColor.dao;

import primaryColor.pojo.Brand;

import java.util.List;

/**
 * @author PrimaryColor
 * @date 2023/1/19
 * @apiNote
 */
public interface BrandDao {
    List<Brand> selectAll();
    void add(Brand brand);
    Brand selectById(int id);
    void update(Brand brand);
    void delete(Brand brand);
    List<Brand> selectByPage(int begin,int size);
    int selectTotal();
    List<Brand> selectByPageAndCondition(int begin,int size, Brand brand);
    int selectTotalByConditon(Brand brand);
}
