package com.youzhong.dao;

import com.youzhong.entity.AppImg;
import com.youzhong.entity.AppImgExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppImgMapper {
    int countByExample(AppImgExample example);

    int deleteByExample(AppImgExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AppImg record);

    int insertSelective(AppImg record);

    List<AppImg> selectByExample(AppImgExample example);

    AppImg selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AppImg record, @Param("example") AppImgExample example);

    int updateByExample(@Param("record") AppImg record, @Param("example") AppImgExample example);

    int updateByPrimaryKeySelective(AppImg record);

    int updateByPrimaryKey(AppImg record);
}