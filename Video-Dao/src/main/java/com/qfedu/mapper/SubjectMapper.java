package com.qfedu.mapper;

import com.qfedu.entity.Subject;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Stream
 * @version 1.0
 * @date 2019/09/21 10:26
 */
public interface SubjectMapper {
    /**
     * 查询课程所属科目
     *
     * @return
     */
    @Select("select * from subject")
    @Results(id = "subjectMap",
            value = {
                    @Result(id = true, property = "id", column = "id"),
                    @Result(property = "subjectName", column = "subject_name")
            })
    List<Subject> selectAllSubject();

    /**
     *
     * @param subjectId
     * @return
     */
    Subject getSubjectById(Integer subjectId);

}
