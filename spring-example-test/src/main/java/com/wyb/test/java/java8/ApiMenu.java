package com.wyb.test.java.java8;

import lombok.Data;

import java.util.List;

/**
 * @author Marcher丶
 */
@Data
public class ApiMenu {

    int id;
    Integer parentId;
    Integer sort;
    List<ApiMenu> children;
}
