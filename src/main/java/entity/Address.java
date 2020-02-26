package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 地址实例
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Address implements Serializable {
    /**
     * 序列化号
     */
    private static final long serialVersionUID = 7564776194312963061L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 国家
     */
    private String country;
    /**
     * 省份
     */
    private String province;
    /**
     * 市
     */
    private String city;
    /**
     * 区域
     */
    private String area;
    /**
     * 街道信息
     */
    private String streetInfo;
    /**
     * 详细地址
     */
    private String detailAddress;
}
