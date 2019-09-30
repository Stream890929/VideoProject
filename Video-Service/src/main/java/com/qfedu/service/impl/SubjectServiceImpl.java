package com.qfedu.service.impl;

import com.qfedu.entity.Subject;
import com.qfedu.mapper.SubjectMapper;
import com.qfedu.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Stream
 * @version 1.0
 * @date 2019/09/21 10:24
 */
@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    SubjectMapper subjectMapper;

    @Override
    public List<Subject> selectAllSubject() {
        return subjectMapper.selectAllSubject ();
    }

    @Override
    public Subject getSubjectById(Integer subjectId) {
        return subjectMapper.getSubjectById(subjectId);
    }

}
