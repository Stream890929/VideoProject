package com.qfedu.mapper;

import com.qfedu.entity.Speaker;

import java.util.List;

/**
 * @author Stream
 * @version 1.0
 * @date 2019/09/18 19:38
 */
public interface SpeakerMapper {
    /**
     * 查询所有的主讲人
     * @return
     */
    List<Speaker> selectAllSpeaker();

    /**
     * 查询所有的主讲人
     * @return
     */
    List<Speaker> selectSpeakerList();

    /**
     * 获取要修改的讲师id
     * @param id
     * @return
     */
    Speaker getSpeakerById(Integer id);

    /**
     * 添加讲师
     * @param speaker
     * @return
     */
    int saveSpeaker(Speaker speaker);

    /**
     * 根据id修改讲师信息
     * @param speaker
     * @return
     */
    int updateSpeakerById(Speaker speaker);

    /**
     * 删除主讲人方法
     * @param id 讲师id
     * @return
     */
    int deleteSpeakerById(Integer id);

}
