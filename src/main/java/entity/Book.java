package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 书本实例
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Book implements Serializable {
    /**
     * 序列化号
     */
    private static final long serialVersionUID = -4507633746397878138L;

    /**
     * 表主键
     */
    private Integer id;

    /**
     * 书本编号
     */
    private Integer bookNo;

    /**
     * 书本名称
     */
    private String bookName;

    /**
     * 书本价格
     */
    private BigDecimal bookPrice;
}
