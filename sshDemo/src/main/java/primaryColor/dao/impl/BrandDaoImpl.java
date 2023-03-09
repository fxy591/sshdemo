package primaryColor.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import primaryColor.dao.BrandDao;
import primaryColor.pojo.Brand;
import primaryColor.utils.HibernateUtils;

import java.util.List;

/**
 * @author PrimaryColor
 * @date 2023/1/19
 * @apiNote
 */
public class BrandDaoImpl implements BrandDao {
    /**
     * 查询所有
     * @return
     */
    @Override
    public List<Brand> selectAll() {
        //System.out.println("selectAlll");
        Session session = HibernateUtils.openSession();
        //System.out.println("hbiber");
        String hql = "from Brand";
        Query query = session.createQuery(hql);
        List<Brand> brands = query.list();
        System.out.println(brands);
        session.close();
        return brands;
    }

    /**
     * 添加
     * @param brand
     */
    @Override
    public void add(Brand brand) {
        Session session = HibernateUtils.openSession();
        session.save(brand);
        session.beginTransaction().commit();
        session.close();
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Override
    public Brand selectById(int id) {
        Session session = HibernateUtils.openSession();
        //String hql = "from Brand where id = "
        Brand brand = (Brand) session.get(Brand.class, id);
        session.close();
        return brand;
    }

    /**
     * 修改数据
     * @param brand
     */
    @Override
    public void update(Brand brand) {
        Session session = HibernateUtils.openSession();
        String sql = "update tb_brand set brand_name = :brandName, company_name = :companyName, ordered = :ordered, description = :desc, status = :status where id = :id";
        Query query = session.createSQLQuery(sql);
        query.setParameter("id", brand.getId());
        query.setParameter("brandName", brand.getBrandName());
        query.setParameter("companyName", brand.getCompanyName());
        query.setParameter("ordered", brand.getOrdered());
        query.setParameter("desc", brand.getDescription());
        query.setParameter("status", brand.getStatus());
        query.executeUpdate();
        session.beginTransaction().commit();
        session.close();
    }

    /**
     * 删除数据
     * @param brand
     */
    @Override
    public void delete(Brand brand) {
        Session session = HibernateUtils.openSession();
        session.delete(brand);
        session.beginTransaction().commit();
        session.close();
    }

    /*
       分页查询
       limit
       参数1 开始索引 = （当前页面-1） * 条数
       参数2 查询的条目数 = 条数
       select * from tb_brand limit 0 , 5

       页面传递参数
       当前页码 currentPage
       每页条数 pageSize

       后端返回
       数据 list
       总条记录数  totalCount  =》 PageBean
     */

    /**
     * 分页查询
     * @param begin
     * @param size
     * @return
     */
    @Override
    public List<Brand> selectByPage(int begin, int size) {
        Session session = HibernateUtils.openSession();
        //System.out.println("begin:"+begin + "size"+size);
        //String sql = "select * from tb_brand limit :begin,:size";
        //Query query = session.createSQLQuery(sql);
        //query.setParameter("begin", begin);
        //query.setParameter("size", size);
        //List<Brand> brands = query.list();
        //System.out.println("123456798"+brands);
        //return brands;
        String hql = "from Brand";
        Query query = session.createQuery(hql);
        query.setFirstResult(begin);
        query.setMaxResults(size);
        return query.list();
    }

    /**
     * 查询总记录数
     * @return
     */
    @Override
    public int selectTotal() {
        Session session = HibernateUtils.openSession();
        String sql = "select count(*) from tb_brand";
        Query query = session.createSQLQuery(sql);
        String str = query.uniqueResult().toString();
        return Integer.parseInt(str);
    }

    /**
     * 分页条件模糊查询
     * @param begin
     * @param size
     * @param brand
     * @return
     */
    @Override
    public List<Brand> selectByPageAndCondition(int begin, int size, Brand brand) {
        System.out.println(brand);
        String sql = "from Brand as b where 1=1 ";
        if(brand.getBrandName() != null && brand.getBrandName() != ""){
            sql = sql + " and b.brandName like '%" + brand.getBrandName() + "%' ";
        }
        if(brand.getCompanyName() != null && brand.getCompanyName() != ""){
            sql = sql + " and b.companyName like '%" + brand.getCompanyName() + "%' ";
        }
        if(brand.getStatus() != null){
            sql = sql + " and b.status =" + brand.getStatus();
        }
        System.out.println(sql);

        Session session = HibernateUtils.openSession();
        Query query = session.createQuery(sql);
        query.setFirstResult(begin);
        query.setMaxResults(size);

        return query.list();
    }

    /**
     * 根据条件查询记录数
     * @param brand
     * @return
     */
    @Override
    public int selectTotalByConditon(Brand brand) {
        String sql = "select count(*) from tb_brand where 1=1 ";
        if(brand.getBrandName() != null && brand.getBrandName() != ""){
            sql = sql + " and brand_name like '%" + brand.getBrandName() + "%' ";
        }
        if(brand.getCompanyName() != null && brand.getCompanyName() != ""){
            sql = sql + " and company_name like '%" + brand.getCompanyName() + "%' ";
        }
        if(brand.getStatus() != null){
            sql = sql + " and status =" + brand.getStatus();
        }

        Session session = HibernateUtils.openSession();
        Query query = session.createSQLQuery(sql);
        String str = query.uniqueResult().toString();
        return Integer.parseInt(str);
        //return 0;
    }

    //@Override
    //public void updateLastLoginDateById(String id) {
    //    if (StringUtils.hasText(id)) {
    //        String sql = "update CORP_USER_INFO set last_login_time=sysdate  where id =:id ";
    //        Query query = this.hibernateUtil.getSession().createSQLQuery(sql);
    //        query.setParameter("id", id);
    //        query.executeUpdate();
    //    }
    //}
    //@Override
    //public UserInfoData findByChsiId(String chsiId) {
    //    if (!StringUtils.hasText(chsiId)) {
    //        return null;
    //    }
    //    String sql = "select a from UserInfoData a where a.chsiId=:chsiId";
    //    return (UserInfoData) this.hibernateUtil.getSession().createQuery(sql).setParameter("chsiId", chsiId)
    //            .uniqueResult();
    //}
}
