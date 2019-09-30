package com.qfedu.controller;

import com.github.pagehelper.PageInfo;
import com.qfedu.entity.Speaker;
import com.qfedu.service.SpeakerService;
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
 * @date 2019/09/19 20:59
 */
@Controller
@RequestMapping("/speaker")
@Api(value = "讲师操作", tags = "讲师操作接口，例如讲师展示方法、添加、修改、删除等")
public class SpeakerController {
    @Autowired
    SpeakerService speakerService;

    /**
     * 主讲人管理界面
     *
     * @param model
     * @param page     页数
     * @param pageSize 行数
     * @return
     */
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "list", notes = "讲师展示方法", httpMethod = "GET，POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "行数", required = true, dataType = "int")
    })
    public String list(Model model, @RequestParam(required = false, defaultValue = "1") int page, @RequestParam(defaultValue = "9") int pageSize) {
        // 主讲人list
        List<Speaker> speakerList = speakerService.selectSpeakerList (page, pageSize);
        // 将获取的list集合导入页面
        PageInfo<Speaker> pageInfo = new PageInfo<Speaker> (speakerList);
        model.addAttribute ("pageInfo", pageInfo);
        return "behind/speakerList";
    }

    /**
     * 跳转至讲师添加界面
     *
     * @return
     */
    @RequestMapping(value = "/addSpeaker", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "addSpeaker", notes = "跳转至添加讲师页面", httpMethod = "GET，POST")
    public String addSpeaker() {
        return "behind/addSpeaker";
    }

    /**
     * 跳转至讲师修改页面
     *
     * @param model
     * @param id    讲师id
     * @return
     */
    @RequestMapping(value = "/editSpeaker/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "editSpeaker", notes = "跳转至修改讲师页面", httpMethod = "GET，POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "讲师id", required = true, dataType = "Integer")
    })
    public String editSpeaker(Model model, @PathVariable("id") Integer id) {
        // 获取需要修改讲师的id
        Speaker speaker = speakerService.getSpeakerById (id);
        model.addAttribute ("speaker", speaker);
        return "behind/addSpeaker";
    }

    /**
     * 保存或修改方法
     *
     * @param speaker
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveOrUpdate", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "saveOrUpdate", notes = "添加和修改讲师方法", httpMethod = "GET，POST")
    public String saveOrUpdate(Speaker speaker) {
        int result = speakerService.saveOrUpdate (speaker);
        return "success";
    }

    /**
     * 主讲人的 删除方法
     *
     * @param id 讲师id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteSpeaker", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "deleteSpeaker", notes = "删除讲师方法", httpMethod = "GET，POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "讲师id", required = true, dataType = "Integer")
    })
    public String deleteSpeaker(Integer id) {
        int result = speakerService.deleteSpeakerById (id);
        return "success";
    }

}
