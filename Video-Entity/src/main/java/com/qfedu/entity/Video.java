package com.qfedu.entity;

import lombok.Data;
import utils.StringUtils;

/**
 * 视频实体类
 * @author Stream
 */
@Data
public class Video {
    private Integer id;
    private String title;
    private String detail;
    private Integer time;
    // private String showTime;
    private String videoUrl;
    private String videoImageUrl;
    private Integer playNum;

    private String speakerName;
    private Integer speakerId;
    private Integer courseId;
    private String courseTitle;

    private Speaker speaker;
    private Course course;

    public Video() {
    }

    public Video(Integer id, String title, String detail, Integer time, String videoUrl, String videoImageUrl, Integer playNum, String speakerName, Integer speakerId, Integer courseId, String courseTitle, Speaker speaker, Course course) {
        this.id = id;
        this.title = title;
        this.detail = detail;
        this.time = time;
        this.videoUrl = videoUrl;
        this.videoImageUrl = videoImageUrl;
        this.playNum = playNum;
        this.speakerName = speakerName;
        this.speakerId = speakerId;
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.speaker = speaker;
        this.course = course;
    }

    public String getShowTime() {
        return StringUtils.getStrTime (time);
    }

}
