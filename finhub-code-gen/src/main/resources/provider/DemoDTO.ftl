package ${conf.providerPackageName}.demo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Demo DTO
 *
 * @author ${conf.author}
 * @version ${conf.version}
 * @since ${conf.createDate}
 */
@Data
public class DemoDTO implements Serializable {

    private static final long serialVersionUID = 5409185459234711691L;

    /**
     * 用户编号
     */
    private Integer id;
    /**
     * 昵称
     */
    private String name;
    /**
     * 性别
     */
    private Integer gender;
}
