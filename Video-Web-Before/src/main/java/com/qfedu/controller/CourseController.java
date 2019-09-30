package com.qfedu.controller;

import com.github.pagehelper.PageInfo;
import com.qfedu.entity.Course;
import com.qfedu.entity.Subject;
import com.qfedu.entity.Video;
import com.qfedu.service.CourseService;
import com.qfedu.service.SubjectService;
import com.qfedu.service.VideoService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Stream
 * @version 1.0
 * @date 2019/09/21 09:58
 */
@Controller
@RequestMapping("/course")
@Api(value = "后台课程操作", tags = "课程操作接口，例如展示数据、添加、修改、删除等")
public class CourseController {
    @Autowired
    CourseService courseService;
    @Autowired
    SubjectService subjectService;
    @Autowired
    VideoService videoService;

    /**
     * 课程管理界面
     *
     * @param model
     * @param page     页数
     * @param pageSize 行数
     * @return
     */
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "list", notes = "后台课程展示方法", httpMethod = "GET，POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "行数", required = true, dataType = "int")
    })
    public String list(Model model, @RequestParam(required = false, defaultValue = "1") int page, @RequestParam(defaultValue = "9") int pageSize) {
        // 获取课程list
        List<Course> courseList = courseService.selectCourseList (page, pageSize);
        // 导入页面
        PageInfo<Course> pageInfo = new PageInfo<Course> (courseList);
        model.addAttribute ("pageInfo", pageInfo);
        // 获取所有科目
        List<Subject> subjectList = subjectService.selectAllSubject ();
        model.addAttribute ("subjectList", subjectList);
        return "behind/courseList";
    }

    /**
     * 跳转至课程添加界面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/addCourse", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "addCourse", notes = "跳转至添加课程页面", httpMethod = "GET，POST")
    public String addCourse(Model model) {
        // 获取所有科目
        List<Subject> subjectList = subjectService.selectAllSubject ();
        model.addAttribute ("subjectList", subjectList);
        return "behind/addCourse";
    }


    /**
     * 跳转至课程修改页面
     *
     * @param model
     * @param id    课程id
     * @return
     */
    @RequestMapping(value = "/editCourse/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "editCourse", notes = "跳转至修改课程页面", httpMethod = "GET，POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "课程id", required = true, dataType = "int")
    })
    public String editCourse(Model model, @PathVariable("id") Integer id) {
        // 获取需要修改课程的id
        Course course = courseService.getCourseById (id);
        model.addAttribute ("course", course);
        // 获取所有科目
        List<Subject> subjectList = subjectService.selectAllSubject ();
        model.addAttribute ("subjectList", subjectList);
        return "behind/addCourse";
    }

    /**
     * 保存或修改方法
     *
     * @param course
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveOrUpdate", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "saveOrUpdate", notes = "添加和修改课程方法", httpMethod = "GET，POST")
    public String saveOrUpdate(Course course) {
        int result = courseService.saveOrUpdate (course);
        if (result > 0) {
            return "success";
        }
        return "fail";
    }

    /**
     * 课程的删除方法
     *
     * @param id 课程id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteCourse", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "deleteCourse", notes = "删除课程方法", httpMethod = "GET，POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "课程id", required = true, dataType = "Integer")
    })
    public String deleteCourse(Integer id) {
        int result = courseService.deleteCourseById (id);
        return "success";
    }

    @RequestMapping(value = "/beforeList", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "showCourseList", notes = "前台课程展示方法", httpMethod = "GET，POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "subjectId", value = "科目id", required = true, dataType = "Integer")
    })
    public String showCourseList(Model model, Integer subjectId) {
        // 获取对应科目
        Subject subject = subjectService.getSubjectById (subjectId);
        List<Course> courseList = courseService.selectCourseBySubjectId (subjectId);
        for (Course course : courseList) {
            List<Video> videoList = videoService.getVideoListByCourseId (course.getId ());
            course.setVideoList (videoList);
        }
        subject.setCourseList (courseList);
        model.addAttribute ("subject", subject);
        return "before/course";
    }

}
