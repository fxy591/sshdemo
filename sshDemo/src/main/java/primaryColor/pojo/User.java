package primaryColor.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * @author PrimaryColor
 * @date 2023/1/18
 * @apiNote
 */
@Data
@Entity
@Table(name = "tb_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
}
