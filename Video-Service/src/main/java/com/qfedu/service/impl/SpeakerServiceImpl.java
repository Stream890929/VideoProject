package com.qfedu.service.impl;

import com.github.pagehelper.PageHelper;
import com.qfedu.entity.Speaker;
import com.qfedu.mapper.SpeakerMapper;
import com.qfedu.service.SpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Stream
 * @version 1.0
 * @date 2019/09/16 15:23
 */
@Service
public class SpeakerServiceImpl implements SpeakerService {
    @Autowired
    SpeakerMapper speakerMapper;

    @Override
    public List<Speaker> selectAllSpeaker() {
        List<Speaker> speakers = speakerMapper.selectAllSpeaker ();
        return speakers;
    }

    @Override
    public List<Speaker> selectSpeakerList(int page, int pageSize) {
        PageHelper.startPage (page, pageSize);
        List<Speaker> speakerList = speakerMapper.selectSpeakerList ();
        return speakerList;
    }

    @Override
    public Speaker getSpeakerById(Integer id) {
        Speaker speaker = speakerMapper.getSpeakerById (id);
        return speaker;
    }

    @Override
    public int saveOrUpdate(Speaker speaker) {
        int result = 0;
        if (speaker.getId () == null) {
            result = speakerMapper.saveSpeaker (speaker);
        } else {
            result = speakerMapper.updateSpeakerById (speaker);
        }

        return result;
    }

    @Override
    public int deleteSpeakerById(Integer id) {
        int result = speakerMapper.deleteSpeakerById (id);
        return result;
    }

}
