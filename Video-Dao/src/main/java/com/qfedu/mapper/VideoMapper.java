package com.qfedu.mapper;

import com.qfedu.entity.Video;
import com.qfedu.entity.VideoQueryVo;

import java.util.List;

/**
 * @author Stream
 * @version 1.0
 * @date 2019/09/17 15:36
 */
public interface VideoMapper {
    /**
     *  1、xml的名字和接口名字 保持一致
     *  2、xml文件路径和接口路径保持一致
     *  3、xml中的namespace 要和接口的全路径保持一致
     *  4、方法名称，参数，返回值类型要和sql语句保持一致
     *
     *  约定大于配置
     */
    List<Video> selectVideoAll();

    /**
     * 查询所有的视频并分页展示
     * @param videoQueryVo 分页
     * @return
     */
    List<Video> selectVideoByQueryVo(VideoQueryVo videoQueryVo);

    /**
     * 获取视频的总条数
     * @param videoQueryVo 计数
     * @return
     */
    int getVideoCount(VideoQueryVo videoQueryVo);

    /**
     * 获取要修改的视频id
     * @param id 视频id
     * @return
     */
    Video getVideoById(Integer id);

    /**
     * 添加方法
     * @param video
     * @return
     */
    int saveVideo(Video video);

    /**
     * 修改方法
     * @param video
     * @return
     */
    int updateVideoById(Video video);

    /**
     * 批量删除方法
     * @param videoQueryVo
     * @return
     */
    int deleteVideosByIds(VideoQueryVo videoQueryVo);

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

    void updateVideoPlayNum(Video video);
}
