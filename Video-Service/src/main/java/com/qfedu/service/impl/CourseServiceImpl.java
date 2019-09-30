package com.qfedu.service.impl;

import com.github.pagehelper.PageHelper;
import com.qfedu.entity.Course;
import com.qfedu.mapper.CourseMapper;
import com.qfedu.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Stream
 * @version 1.0
 * @date 2019/09/16 11:01
 */
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseMapper courseMapper;

    @Override
    public List<Course> selectAllCourse() {
        return courseMapper.selectAllCourse ();
    }

    @Override
    public List<Course> selectCourseList(int page, int pageSize) {
        PageHelper.startPage (page, pageSize);
        return courseMapper.selectCourseList ();
    }

    /**
     * 根据id修改课程信息
     *
     * @param id
     * @return
     */
    @Override
    public Course getCourseById(Integer id) {
        return courseMapper.getCourseById (id);
    }

    @Override
    public int saveOrUpdate(Course course) {
        int result = 0;
        if (course.getId () == null) {
            result = courseMapper.saveCourse (course);
        } else {
            result = courseMapper.updateCourseById (course);
        }
        return result;
    }

    /**
     * 删除课程
     *
     * @param id 课程id
     * @return
     */
    @Override
    public int deleteCourseById(Integer id) {
        return courseMapper.deleteCourseById (id);
    }

    /**
     * 根据科目id获取课程
     * @param subjectId 科目id
     * @return
     */
    @Override
    public List<Course> selectCourseBySubjectId(Integer subjectId) {
        return  courseMapper.selectCourseBySubjectId(subjectId);
    }

}
