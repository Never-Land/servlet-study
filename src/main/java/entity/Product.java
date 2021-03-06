package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品实例
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Product implements Serializable {
    /**
     * 序列化号
     */
    private static final long serialVersionUID = 2601865709190134387L;
    /**
     * 商品ID
     */
    private Integer id;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品描述
     */
    private String description;
    /**
     * 商品单价
     */
    private BigDecimal price;
}
