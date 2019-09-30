package com.qfedu.controller;

import com.qfedu.entity.Course;
import com.qfedu.entity.Speaker;
import com.qfedu.entity.Video;
import com.qfedu.entity.VideoQueryVo;
import com.qfedu.service.CourseService;
import com.qfedu.service.SpeakerService;
import com.qfedu.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import utils.Page;
import utils.UUIDUtils;

import java.io.File;
import java.util.List;

/**
 * @author Stream
 */
@Controller
@RequestMapping("/beforeVideo")
@Api(value = "前台视频操作", tags = "前台视频操作接口，例如视频展示方法、播放次数修改")
public class BeforeVideoController {
    @Autowired
    CourseService courseService;
    @Autowired
    VideoService videoService;



}
