package com.youzhong.config;

import com.youzhong.controller.DataCaptureController;
import com.youzhong.entity.App;
import com.youzhong.entity.AppImg;
import com.youzhong.service.AppImgService;
import com.youzhong.service.AppService;
import com.youzhong.util.HttpUtil;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DownThread extends Thread {

    private Integer startNum;
    private Integer endNum;
    private AppService appService;
    private AppImgService appImgService;

    public DownThread(Integer startNum, Integer endNum, AppService appService, AppImgService appImgService) {
        this.startNum = startNum;
        this.endNum = endNum;
        this.appService = appService;
        this.appImgService = appImgService;
    }

    public AppService getAppService() {
        return appService;
    }

    public void setAppService(AppService appService) {
        this.appService = appService;
    }

    public Integer getStartNum() {
        return startNum;
    }

    public void setStartNum(Integer startNum) {
        this.startNum = startNum;
    }

    public Integer getEndNum() {
        return endNum;
    }

    public void setEndNum(Integer endNum) {
        this.endNum = endNum;
    }

    @Override
    public void run() {
        List<App> list = new ArrayList<>();
        for (int i = startNum; i <= endNum; i++) {
            String url = "http://mm.10086.cn/android/software/qbrj?p=" + i;
            try {

                Parser parser = new Parser(url);
                parser.setEncoding("UTF-8");
                //拿到所有的超连接
                HasAttributeFilter hasAttributeFilter = new HasAttributeFilter("class", "sub_column_jump");
                NodeList parse = parser.parse(hasAttributeFilter);
                for (int j = 0; j < parse.size(); j++) {
                    TagNode tagNode = (TagNode) parse.elementAt(j);
                    String href = tagNode.getAttribute("href");

                    App app = new DataCaptureController().data("http://mm.10086.cn" + href);
                    app.setId(new Random().nextInt(1000000000));
                    appService.insert(app);
                    Parser parser1 = new Parser("http://mm.10086.cn" + href);

                    HasAttributeFilter imgeFilter = new HasAttributeFilter("class", "true_pie_pic");
                    TagNameFilter tagNameFilter = new TagNameFilter("div");
                    AndFilter andFilter1 = new AndFilter(imgeFilter, tagNameFilter);

                    NodeList imgeList = parser1.parse(andFilter1);

                    //给图片表中加数据
                    AppImg appImg = new AppImg();
                    appImg.setAppid(app.getId());
                    for (int h = 0; h < imgeList.size(); h++) {

                        TagNode tagNode1 = (TagNode) imgeList.elementAt(h).getChildren().elementAt(0);
                        String imgeUrl = tagNode1.getAttribute("src");

                        HttpUtil.httpDownload(imgeUrl, "D:/code/" + imgeUrl.substring(imgeUrl.lastIndexOf("/") + 1));
                        appImg.setUrl("D:/code/" + imgeUrl.substring(imgeUrl.lastIndexOf("/") + 1));

                        appImgService.save(appImg);
                    }

                }
            } catch (ParserException e) {
                e.printStackTrace();
            }
        }


    }
}
