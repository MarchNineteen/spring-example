package com.wyb.mybatis.dao.model;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "springboot.user_ex")
public class UserExDo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String country;

    private static final long serialVersionUID = 1L;
}