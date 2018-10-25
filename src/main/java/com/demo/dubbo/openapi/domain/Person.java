package com.demo.dubbo.openapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 接口请求实体(必须序列化)
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Serializable {
    private static final long serialVersionUID = 7615614884509926232L;

    /** 姓名 */
    private String name;

    /** 年龄 */
    private Integer age;

}
