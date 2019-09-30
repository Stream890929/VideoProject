package com.qfedu.service;

import com.qfedu.entity.Subject;

import java.util.List;

/**
 * @author Stream
 * @version 1.0
 * @date 2019/09/21 10:22
 */
public interface SubjectService {
    /**
     * 查询所有的科目
     * @return
     */
    List<Subject> selectAllSubject();

    Subject getSubjectById(Integer subjectId);
}
