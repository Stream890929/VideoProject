package com.qfedu.service;

import com.qfedu.entity.Speaker;

import java.util.List;

/**
 * @author Stream
 * @version 1.0
 * @date 2019/09/16 11:00
 */
public interface SpeakerService {
    List<Speaker> selectAllSpeaker();

    /**
     * 查询所有的主讲人
     * @param page 页数
     * @param pageSize 行数
     * @return
     */
    List<Speaker> selectSpeakerList(int page, int pageSize);

    /**
     * 获取要修改的讲师id
     * @param id
     * @return
     */
    Speaker getSpeakerById(Integer id);

    /**
     * 保存或修改方法
     * @param speaker
     * @return
     */
    int saveOrUpdate(Speaker speaker);

    /**
     * 删除方法
     * @param id 讲师id
     * @return
     */
    int deleteSpeakerById(Integer id);

}
