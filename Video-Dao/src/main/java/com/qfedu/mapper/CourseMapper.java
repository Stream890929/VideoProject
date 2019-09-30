package com.qfedu.mapper;

import com.qfedu.entity.Course;

import java.util.List;

/**
 * @author Stream
 * @version 1.0
 * @date 2019/09/18 20:57
 */
public interface CourseMapper {
    /**
     * 查询所有的课程
     * @return
     */
    List<Course> selectAllCourse();

    /**
     * 查询所有的课程
     * @return
     */
    List<Course> selectCourseList();

    /**
     * 获取要修改课程的id
     * @param id 课程id
     * @return
     */
    Course getCourseById(Integer id);

    /**
     * 添加课程
     * @param course
     * @return
     */
    int saveCourse(Course course);

    /**
     * 修改课程
     * @param course
     * @return
     */
    int updateCourseById(Course course);

    /**
     * 删除课程
     * @param id 课程id
     * @return
     */
    int deleteCourseById(Integer id);

    /**
     * 根据科目id查询该科目所有的课程
     * @param subjectId 科目id
     * @return
     */
    List<Course> selectCourseBySubjectId(Integer subjectId);

    
}
