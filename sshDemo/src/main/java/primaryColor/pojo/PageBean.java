package primaryColor.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 完成分页查询
 * @author PrimaryColor
 * @date 2023/2/1
 * @apiNote
 */
@Setter
@Getter
public class PageBean<T> { // 自定义泛型
    // 总记录数
    private int totalCount;
    // 当前页面数据
    private List<T> rows;
}
