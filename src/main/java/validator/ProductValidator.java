package validator;

import form.ProductForm;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 检验页面输入的商品信息
 */
public class ProductValidator {
    public List<String> validateProduct(ProductForm productForm){
        //错误信息集合
        List<String> errorList = new ArrayList<>();
        String name = productForm.getName();
        if(StringUtils.isBlank(name)){
            errorList.add("Product must have a name");
        }
        String price = productForm.getPrice();
        if(StringUtils.isBlank(price)){
            errorList.add("Product must have a price");
        }else{
            try {
                Double.valueOf(price);
            }catch(NumberFormatException e){
                errorList.add("Product must have all number");
            }
        }
        return errorList;
    }
}
