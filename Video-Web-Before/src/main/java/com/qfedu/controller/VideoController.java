package com.qfedu.controller;

import com.qfedu.entity.*;
import com.qfedu.service.CourseService;
import com.qfedu.service.SpeakerService;
import com.qfedu.service.VideoService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import utils.Page;
import utils.UUIDUtils;

import java.io.File;
import java.util.List;

/**
 * @author Stream
 */
@Controller
@RequestMapping("/video")
@Api(value = "视频操作", tags = "视频操作接口，例如视频展示方法、添加、修改、删除、查询")
public class VideoController {
    @Autowired
    CourseService courseService;
    @Autowired
    SpeakerService speakerService;
    @Autowired
    VideoService videoService;
    @Value("${IMG_PATH}")
    String imgPath;
    @Value("${IMG_URL}")
    String imgUrl;

    /**
     * 视频管理界面
     *
     * @param videoQueryVo 查询实体
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "showList", notes = "视频展示方法", httpMethod = "GET，POST")
    public String showList(Model model, VideoQueryVo videoQueryVo) {
        // 页数，行数
        videoQueryVo.setBegin ((videoQueryVo.getPage () - 1) * videoQueryVo.getRows ());
        List<Video> videos = videoService.selectListByQueryVo (videoQueryVo);
        //分页
        Page<Video> videoPage = new Page<Video> ();
        // 数据总数
        videoPage.setTotal (videoService.getTotalNum (videoQueryVo));
        videoPage.setPage (videoQueryVo.getPage ());
        videoPage.setSize (videoQueryVo.getRows ());
        videoPage.setRows (videos);
        model.addAttribute ("page", videoPage);

        // 获取主讲人和课程
        List<Speaker> speakerList = speakerService.selectAllSpeaker ();
        model.addAttribute ("speakerList", speakerList);
        List<Course> courseList = courseService.selectAllCourse ();
        model.addAttribute ("courseList", courseList);
        return "behind/videoList";
    }

    /**
     * 跳转至添加界面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/addVideo", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "addVideo", notes = "跳转至添加视频页面", httpMethod = "GET，POST")
    public String addVideo(Model model) {
        // 获取主讲人和课程
        List<Speaker> speakerList = speakerService.selectAllSpeaker ();
        model.addAttribute ("speakerList", speakerList);
        List<Course> courseList = courseService.selectAllCourse ();
        model.addAttribute ("courseList", courseList);
        return "behind/addVideo";
    }

    /**
     * 跳转至修改界面
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/editVideo/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "editVideo", notes = "跳转至修改视频页面", httpMethod = "GET,POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "视频id", required = true, dataType = "Integer")
    })
    public String editVideo(Model model, @PathVariable("id") Integer id) {
        // 获取需要修改视频的id
        Video video = videoService.getVideoById (id);
        model.addAttribute ("video", video);

        // 获取主讲人和课程
        List<Speaker> speakerList = speakerService.selectAllSpeaker ();
        model.addAttribute ("speakerList", speakerList);
        List<Course> courseList = courseService.selectAllCourse ();
        model.addAttribute ("courseList", courseList);
        return "/behind/addVideo";
    }

    /**
     * 文件上传方法
     *
     * @param headImg
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/upload", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "uploadImg", notes = "图片上传方法", httpMethod = "GET,POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "headImg", value = "图片", required = true, dataType = "MultipartFile")
    })
    public String uploadImg(MultipartFile headImg) {
        String oldName = headImg.getOriginalFilename ();
        // 切割旧名称，获取后缀名
        String suffix = oldName.substring (oldName.lastIndexOf ("."));
        // 合成新名称
        String newFileName = UUIDUtils.getUUID () + suffix;

        try {
            //调用此方法，完成上传功能
            headImg.transferTo (new File (imgPath, newFileName));
        } catch (Exception e) {
            e.printStackTrace ();
        }

        // 文件的虚拟地址
        String headImgUrl = imgUrl + newFileName;
        return headImgUrl;
    }

    /**
     * 添加，修改方法
     *
     * @param video
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveOrUpdate", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "saveOrUpdate", notes = "添加和修改视频方法", httpMethod = "GET,POST")
    public String saveOrUpdate(Video video) {
        int result = videoService.saveOrUpdate (video);
        return "success";
    }

    /**
     * 批量删除方法
     *
     * @param ids 将要删除视频的id数组
     * @return
     */
    @RequestMapping(value = "/delBatchVideos", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "delBatchVideos", notes = "批量删除视频方法", httpMethod = "GET,POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "批量删除视频的id数组", required = true, dataType = "Integer[]")
    })
    public String delBatchVideos(Integer[] ids) {
        int result = videoService.deleteBatchVideos (ids);
        return "redirect:/video/list";
    }

    /**
     * 删除方法
     *
     * @param id 将要删除视频的id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteVideo", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "deleteVideo", notes = "删除视频方法", httpMethod = "GET,POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "视频id", required = true, dataType = "Integer")
    })
    public String deleteVideo(Integer id) {
        int result = videoService.deleteVideoById (id);
        return "success";
    }

    @RequestMapping(value = "/showVideo")
    public String showVideo(Model model, Integer videoId, String subjectName) {
        Video video = videoService.getVideoById (videoId);
        model.addAttribute ("video", video);
        model.addAttribute ("subjectName", subjectName);

        Integer courseId = video.getCourseId ();
        Course course = courseService.getCourseById (courseId);
        List<Video> videoList = videoService.getVideoListByCourseId (courseId);
        course.setVideoList (videoList);

        model.addAttribute ("course", course);
        return "before/section";
    }

    @ResponseBody
    @RequestMapping(value = "/updatePlayNum")
    public void updatePlayNum(int videoId, int playNum) {
        Video video = new Video ();
        video.setId (videoId);
        video.setPlayNum (playNum + 1);
        videoService.addPlayNym (video);
    }

}
