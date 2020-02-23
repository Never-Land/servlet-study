package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 商品清单
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
public class ShoppingItme {
    /**
     * 商品
     */
    private Product product;
    /**
     * 商品数量
     */
    private int quantity;
}
