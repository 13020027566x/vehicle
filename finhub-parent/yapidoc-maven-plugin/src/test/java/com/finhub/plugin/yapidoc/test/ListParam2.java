package com.finhub.plugin.yapidoc.test;

import lombok.Data;

import java.util.List;

/**
 * @version V1.0
 * @date 2019-06-05 16:38
 */
@Data
public class ListParam2 {
    private List<List<CreateParam2>> testList;
}
