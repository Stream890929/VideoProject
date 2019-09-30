package com.qfedu.entity;

import lombok.Data;

import java.util.List;

/**
 * 此实体是个为了接收查询条件方便，产生的分页实体
 * @author Stream
 * @version 1.0
 * @date 2019/09/16 10:15
 */
@Data
public class VideoQueryVo {
    private int page = 1;
    private int rows = 9;
    private int begin = 0;

    private String title;
    private int speakerId;
    private int courseId;
    private List<Integer> ids;

    public VideoQueryVo() {
    }

    public VideoQueryVo(int page, int rows, int begin, String title, int speakerId, int courseId, List<Integer> ids) {
        this.page = page;
        this.rows = rows;
        this.begin = begin;
        this.title = title;
        this.speakerId = speakerId;
        this.courseId = courseId;
        this.ids = ids;
    }

}
