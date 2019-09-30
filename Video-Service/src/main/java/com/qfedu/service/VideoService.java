package com.qfedu.service;

import com.qfedu.entity.Video;
import com.qfedu.entity.VideoQueryVo;

import java.util.List;

/**
 * @author Stream
 * @version 1.0
 * @date 2019/09/18 10:54
 */
public interface VideoService {
    /**
     * 查询所有视频并分页
     * @param videoQueryVo
     * @return
     */
    List<Video> selectListByQueryVo(VideoQueryVo videoQueryVo);

    /**
     * 视频总条数
     * @param videoQueryVo
     * @return
     */
    int getTotalNum(VideoQueryVo videoQueryVo);

    /**
     * 获取要修改的视频id
     * @param id
     * @return
     */
    Video getVideoById(Integer id);

    /**
     * 保存或修改方法
     * @param video
     * @return
     */
    int saveOrUpdate(Video video);

    /**
     * 批量删除方法
     * @param ids 视频id 数组
     * @return
     */
    int deleteBatchVideos(Integer[] ids);

    /**
     * 删除方法
     * @param id 视频id
     * @return
     */
    int deleteVideoById(Integer id);

    /**
     * 根据课程id查询该课程的所有视频
     * @param id 课程id
     * @return
     */
    List<Video> getVideoListByCourseId(Integer id);

    void addPlayNym(Video video);
}
