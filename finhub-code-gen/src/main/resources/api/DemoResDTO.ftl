package ${conf.rpcApiPackageName}.demo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Demo ResDTO
 *
 * @author ${conf.author}
 * @version ${conf.version}
 * @since ${conf.createDate}
 */
@Data
public class DemoResDTO implements Serializable {

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

    public Integer getId() {
        return id;
    }

    public DemoResDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public DemoResDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getGender() {
        return gender;
    }

    public DemoResDTO setGender(Integer gender) {
        this.gender = gender;
        return this;
    }
}
