package primaryColor.service.impl;

import primaryColor.dao.impl.BrandDaoImpl;
import primaryColor.pojo.Brand;
import primaryColor.pojo.PageBean;
import primaryColor.service.BrandService;

import java.util.List;

/**
 * @author PrimaryColor
 * @date 2023/1/19
 * @apiNote
 */
public class BrandServiceImpl implements BrandService {
    /**
     * 查询所有
     * @return
     */
    @Override
    public List<Brand> selectAll(){
        // 调用BrandDaoImpl.selectAlll()
        BrandDaoImpl brandDaoImpl = new BrandDaoImpl();
        return brandDaoImpl.selectAll();
    }

    /**
     * 添加
     * @param brand
     */
    @Override
    public void add(Brand brand){
        BrandDaoImpl brandDaoImpl = new BrandDaoImpl();
        brandDaoImpl.add(brand);
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Override
    public Brand selectById(int id){
        BrandDaoImpl brandDaoImpl = new BrandDaoImpl();
        return brandDaoImpl.selectById(id);
    }

    /**
     * 修改数据
     * @param brand
     */
    @Override
    public void update(Brand brand){
        BrandDaoImpl brandDaoImpl = new BrandDaoImpl();
        brandDaoImpl.update(brand);
    }

    /**
     * 删除数据
     * @param brand
     */
    @Override
    public void delete(Brand brand){
        BrandDaoImpl brandDaoImpl = new BrandDaoImpl();
        brandDaoImpl.delete(brand);
    }

    /**
     * 分页查询
     * @param currentPage 当前页码
     * @param pageSize 条目数
     * @return
     */
    @Override
    public PageBean<Brand> selectByPage(int currentPage, int pageSize) {
        BrandDaoImpl brandDaoImpl = new BrandDaoImpl();
        // 计算开始索引
        int start = (currentPage-1) * pageSize;
        // 获取数据
        List<Brand> rows = (List<Brand>) brandDaoImpl.selectByPage(start, pageSize);
        // 获取总记录数
        int totalCount = brandDaoImpl.selectTotal();
        // 封装对象
        PageBean<Brand> pageBean = new PageBean<Brand>();
        pageBean.setRows(rows);
        System.out.println(rows);
        pageBean.setTotalCount(totalCount);
        // 返回
        return pageBean;
    }

    @Override
    public PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand) {
        BrandDaoImpl brandDaoImpl = new BrandDaoImpl();
        // 计算开始索引
        int start = (currentPage-1) * pageSize;
        System.out.println("service"+brand);
        // 获取数据
        List<Brand> rows = (List<Brand>) brandDaoImpl.selectByPageAndCondition(start, pageSize, brand);
        // 获取总记录数
        int totalCount = brandDaoImpl.selectTotalByConditon(brand);
        // 封装对象
        PageBean<Brand> pageBean = new PageBean<Brand>();
        pageBean.setRows(rows);
        System.out.println(rows);
        pageBean.setTotalCount(totalCount);
        // 返回
        return pageBean;
    }
}