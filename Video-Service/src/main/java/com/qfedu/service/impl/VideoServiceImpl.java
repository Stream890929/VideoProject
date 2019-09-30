package com.qfedu.service.impl;

import com.qfedu.entity.Video;
import com.qfedu.entity.VideoQueryVo;
import com.qfedu.mapper.VideoMapper;
import com.qfedu.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author Stream
 * @version 1.0
 * @date 2019/09/18 10:55
 */
@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    VideoMapper videoMapper;

    @Override
    public List<Video> selectListByQueryVo(VideoQueryVo videoQueryVo) {
        return videoMapper.selectVideoByQueryVo (videoQueryVo);
    }

    @Override
    public int getTotalNum(VideoQueryVo videoQueryVo) {
        return videoMapper.getVideoCount (videoQueryVo);
    }

    @Override
    public Video getVideoById(Integer id) {
        return videoMapper.getVideoById(id);
    }

    @Override
    public int saveOrUpdate(Video video) {
        int result = 0;
        try {
            if (video.getId () == null){
                result = videoMapper.saveVideo (video);
            }else{
                result = videoMapper.updateVideoById(video);
            }
        } catch (Exception e) {
            e.printStackTrace ();
        } finally { }
        return result;
    }

    @Override
    public int deleteBatchVideos(Integer[] ids) {
        VideoQueryVo videoQueryVo = new VideoQueryVo ();
        // Arrays.asList(ids)  将数组变成一个list集合，但是该集合是只读的
        videoQueryVo.setIds (Arrays.asList (ids));
        return videoMapper.deleteVideosByIds (videoQueryVo);
    }

    @Override
    public int deleteVideoById(Integer id) {
        return videoMapper.deleteVideoById (id);
    }

    /**
     * 根据课程id查询该课程的所有视频
     * @param id 课程id
     * @return
     */
    @Override
    public List<Video> getVideoListByCourseId(Integer id) {
        return videoMapper.getVideoListByCourseId(id);
    }

    @Override
    public void addPlayNym(Video video) {
        videoMapper.updateVideoPlayNum(video);
    }

}
