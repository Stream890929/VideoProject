package com.qfedu.entity;

import lombok.Data;

/**
 * 主讲人实体类
 * @author Stream
 * @version 1.0
 * @date 2019/09/16 10:15
 */
@Data
public class Speaker {
    private Integer id;
    private String speakerName;
    private String speakerDesc;
    private String speakerJob;
    private String headImgUrl;

    public Speaker() {
    }

    public Speaker(Integer id, String speakerName, String speakerDesc, String speakerJob, String headImgUrl) {
        this.id = id;
        this.speakerName = speakerName;
        this.speakerDesc = speakerDesc;
        this.speakerJob = speakerJob;
        this.headImgUrl = headImgUrl;
    }

}
