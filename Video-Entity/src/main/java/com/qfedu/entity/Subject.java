package com.qfedu.entity;

import lombok.Data;

import java.util.List;

/**
 * 课程科目实体类
 * @author Stream
 * @version 1.0
 * @date 2019/09/21 10:19
 */
@Data
public class Subject {
    private Integer id;
    private String subjectName;

    private List<Course> courseList;

    public Subject() {
    }

    public Subject(Integer id, String subjectName) {
        this.id = id;
        this.subjectName = subjectName;
    }

}
