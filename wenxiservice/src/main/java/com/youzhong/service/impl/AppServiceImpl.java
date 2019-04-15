package com.youzhong.service.impl;

import com.youzhong.dao.AppMapper;
import com.youzhong.entity.App;
import com.youzhong.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppServiceImpl implements AppService {

    @Autowired
    private AppMapper appMapper;

    @Override
    public void save(List<App> apps) {
        appMapper.saveApps(apps);
    }

    @Override
    public void add(App app1) {
        appMapper.insertSelective(app1);
    }

    @Override
    public void insert(App app) {
        appMapper.insertSelective(app);
    }
}
