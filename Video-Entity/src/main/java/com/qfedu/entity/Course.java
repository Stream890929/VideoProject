package com.qfedu.entity;

import lombok.Data;

import java.util.List;

/**
 * 课程实体类
 * @author Stream
 * @version 1.0
 * @date 2019/09/16 11:01
 */
@Data
public class Course {
    private Integer id;
    private String courseTitle;
    private String courseDesc;
    private Integer subjectId;
    private String subjectName;

    private List<Video> videoList;
    private Subject subject;

    public Course() {
    }

    public Course(Integer id, String courseTitle, String courseDesc, Integer subjectId, String subjectName) {
        this.id = id;
        this.courseTitle = courseTitle;
        this.courseDesc = courseDesc;
        this.subjectId = subjectId;
        this.subjectName = subjectName;
    }

}
