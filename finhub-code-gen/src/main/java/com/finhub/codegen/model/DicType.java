package com.finhub.codegen.model;

import lombok.Data;

import java.util.List;

/**
 * @author Mickey
 * @version 1.0
 * @since 2015/05/13 17:34
 */
@Data
public class DicType {

    String name;

    String code;

    List<Dic> dics;
}
