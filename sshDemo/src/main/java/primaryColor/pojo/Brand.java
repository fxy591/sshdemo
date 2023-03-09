package primaryColor.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * @author PrimaryColor
 * @date 2023/1/19
 * @apiNote
 */
@Data
@Entity
@Table(name = "tb_brand")
public class Brand {
    // id 主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    // 品牌名称
    @Column(name = "brand_name")
    private String brandName;
    // 企业名称
    @Column(name = "company_name")
    private String companyName;
    // 排序字段
    @Column(name = "ordered")
    private Integer ordered;
    // 描述信息
    @Column(name = "description")
    private String description;
    // 状态：0：禁用  1：启用
    @Column(name = "status")
    private Integer status;

    public String getStatusStr(){
        if(this.status == 1){
            return  "启用";
        }
        return "禁用";
    }

}
