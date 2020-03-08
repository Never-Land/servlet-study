package form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 商品信息与页面进行交互的实例
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ProductForm {
    /**
     * 商品ID
     */
    private String id;
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
    private String price;
}
