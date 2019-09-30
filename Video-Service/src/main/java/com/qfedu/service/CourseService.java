package com.qfedu.service;

import com.qfedu.entity.Course;
import com.qfedu.entity.Speaker;

import java.util.List;

/**
 * @author Stream
 * @version 1.0
 * @date 2019/09/16 11:00
 */
public interface CourseService {
    List<Course> selectAllCourse();

    /**
     * 查询所有课程
     * @param page 页数
     * @param pageSize 行数
     * @return
     */
    List<Course> selectCourseList(int page, int pageSize);

    /**
     * 根据id修改课程信息
     * @param id 课程id
     * @return
     */
    Course getCourseById(Integer id);

    /**
     * 保存或修改方法
     * @param course
     * @return
     */
    int saveOrUpdate(Course course);

    /**
     * 删除课程
     * @param id 课程id
     * @return
     */
    int deleteCourseById(Integer id);

    /**
     * 根据科目id获取课程
     * @param subjectId 科目id
     * @return
     */
    List<Course> selectCourseBySubjectId(Integer subjectId);

}
