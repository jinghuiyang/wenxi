package com.youzhong.service.impl;

import com.youzhong.dao.AppImgMapper;
import com.youzhong.entity.AppImg;
import com.youzhong.service.AppImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppImgServiceImpl implements AppImgService {

    @Autowired
    private AppImgMapper appImgMapper;

    @Override
    public void save(AppImg appImg) {
        appImgMapper.insert(appImg);
    }
}
