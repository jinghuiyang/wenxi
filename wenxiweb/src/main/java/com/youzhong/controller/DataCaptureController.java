package com.youzhong.controller;

import com.youzhong.config.DownThread;
import com.youzhong.entity.App;
import com.youzhong.service.AppImgService;
import com.youzhong.service.AppService;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 抓取网站的数据
 */
@Controller
@RequestMapping("down")
public class DataCaptureController {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    private AppService appService;
    @Autowired
    private AppImgService appImgService;

    //抓取每个应用的数据
    public App data(String appUrl) {
        App app = new App();


        try {
            //创建类
            Parser parser = new Parser(appUrl);
            //设置字符集编码
            parser.setEncoding("UTF-8");
            //得到应用名称
            HasAttributeFilter hasAttributeFilter = new HasAttributeFilter("class", "info_front_name ovhelipse");
            NodeList parse = parser.parse(hasAttributeFilter);
            String name = parse.elementAt(0).getFirstChild().getText();
            app.setName(name);
            System.out.println(name);
            //重置
            parser.reset();
            //解析版本
            //属性过滤器
            HasAttributeFilter version = new HasAttributeFilter("class", "appl_tor_msg");
            //标签过滤器
            TagNameFilter div = new TagNameFilter("div");
            //组合过滤器
            AndFilter andFilter = new AndFilter(version, div);
            //组合后得到的解析
            NodeList nodeList = parser.parse(andFilter);
            //得到版本号所在的父div（一共有三层）：一层
            for (int i = 0; i < nodeList.size(); i++) {
                if (i == 0) {
                    //一层
                    NodeList children = nodeList.elementAt(i).getChildren();
                    //开始循坏二层
                    for (int j = 0; j < children.size(); j++) {
                        if (j == 2) {
                            NodeList childrens = children.elementAt(j).getChildren();
                            if (childrens.size() > 0) {
                                //得到第三层
                                String text1 = childrens.elementAt(1).getFirstChild().getText();//得到版本号
                                app.setVersion(text1);

                            }

                        }
                        if (j == 3) {

                            NodeList childrens = children.elementAt(j).getChildren();
                            if (childrens.size() > 0) {
                                //得到开发者信息(里面有一个超链接)
                                NodeList text1 = childrens.elementAt(1).getChildren();
                                //得到超链接的文本
                                String developer = text1.elementAt(0).getFirstChild().getText();
                                app.setDeveloper(developer);

                            }

                        }
                        if (j == 4) {
                            NodeList childrens = children.elementAt(j).getChildren();
                            if (childrens.size() > 0) {
                                //得到第三层
                                //得到更新时间
                                String updateTime = childrens.elementAt(1).getFirstChild().getText();
                                try {
                                    app.setUpdatedate(sdf.parse(updateTime));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }


                            }

                        }

                    }
                }
            }

            //开始解析应用大小（appsize）
            parser.reset();
            HasAttributeFilter appSizeClass = new HasAttributeFilter("class", "get_user_know");

            NodeList appSizeList = parser.parse(appSizeClass);
            String text1 = appSizeList.elementAt(0).getLastChild().getText();
            app.setAppsize(text1.substring(text1.indexOf("趣") + 4));
            //得到下载大小


            parser.reset();
            //应用类型过滤器
            HasAttributeFilter appTypeFilter = new HasAttributeFilter("class", "tool_bel_clap");
            NodeList appTypeList = parser.parse(appTypeFilter);
            String appTypeText = appTypeList.elementAt(0).getFirstChild().getText();
            //得到类型text
            app.setApptype(appTypeText);
            System.out.println(appTypeText);
            //得到简介内容
            parser.reset();
            HasAttributeFilter contextFilter = new HasAttributeFilter("class", "flow_res_msg");
            NodeList contextList = parser.parse(contextFilter);
            //得到简介内容
            String context = contextList.elementAt(0).getFirstChild().getText();
            app.setContent(context);

            //添加数据库

          /*  appService.insert(app);
            parser.reset();

            HasAttributeFilter imgeFilter = new HasAttributeFilter("class", "true_pie_pic");
            TagNameFilter tagNameFilter = new TagNameFilter("div");
            AndFilter andFilter1 = new AndFilter(imgeFilter, tagNameFilter);

            NodeList imgeList = parser.parse(andFilter1);

            //给图片表中加数据
            AppImg appImg = new AppImg();

            for (int i = 0; i < imgeList.size(); i++) {

                TagNode tagNode1 = (TagNode) imgeList.elementAt(i).getChildren().elementAt(0);
                String imgeUrl = tagNode1.getAttribute("src");
                System.out.println(imgeUrl.substring(imgeUrl.lastIndexOf("/") + 1));
                HttpUtil.httpDownload(imgeUrl, "D:/code/" + imgeUrl.substring(imgeUrl.lastIndexOf("/") + 1));
                appImg.setUrl("D:/code/" + imgeUrl.substring(imgeUrl.lastIndexOf("/") + 1));

               appImgService.save(appImg);
            }*/
            return app;

        } catch (ParserException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("down")
    public void downDate() {
        String appUrl = "http://mm.10086.cn/android/software/qbrj";
        HasAttributeFilter attributeFilter = new HasAttributeFilter("class", "last");
        try {
            Parser parser = new Parser(appUrl);
            NodeList parse = parser.parse(attributeFilter);
            TagNode tagNode = (TagNode) parse.elementAt(0);
            String[] hrefs = tagNode.getAttribute("href").split("=");
            String href = hrefs[hrefs.length - 1];
            //得到的总页数
            Integer totalPage = Integer.parseInt(href);

            //单个线程抓取的个数
            Integer threadTotalPage = 10;
            //需要几个线程
            Integer threadNum = totalPage / threadTotalPage + (totalPage % threadTotalPage == 0 ? 0 : 1);


            for (int i = 0; i < threadNum; i++) {
                //开始多线程抓取数据
                DownThread downThread = new DownThread((i * threadTotalPage) + 1, (i + 1) * threadTotalPage > totalPage ? totalPage : (0 + 1) * threadTotalPage, appService, appImgService);
                //启动线程
                downThread.start();
            }
        } catch (ParserException e) {
            e.printStackTrace();
        }


    }


    public static void main(String[] args) {
     /* String appUrl="http://mm.10086.cn/android/info/300009240030.html?from=www&fw=492";
      new DataCaptureController().data(appUrl);*/
        new DataCaptureController().downDate();
    }
}
