package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 用户类实体
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Customer implements Serializable {
    /**
     * 序列化号
     */
    private static final long serialVersionUID = -4203345121731466748L;
    /**
     * 用户ID
     */

    private Integer id;
    /**
     * 用户名
     */
    private String name;
    /**
     * 用户所在城市
     */
    private String city;
}
