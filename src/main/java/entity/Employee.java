package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * jsp:setProperty、jsp:getProperty动作标签演示使用对象、其他演示实例
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Employee implements Serializable {
    /**
     * 序列化号
     */
    private static final long serialVersionUID = -6193225494959983106L;

    /**
     * 雇员ID
     */
    private Integer id;

    /**
     * 雇员姓名
     */
    private String name;
    /**
     * 雇员地址
     */
    private Address address;
}
