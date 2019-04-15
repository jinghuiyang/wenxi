package com.youzhong.service;

import com.youzhong.entity.App;

import java.util.List;

public interface AppService {
    void save(List<App> app);

    void add(App app1);

    void insert(App app);
}
