package example;

import xyz.itbang.gspider.Page;
import xyz.itbang.gspider.Spider;
import xyz.itbang.gspider.handler.AbstractHandler;
import xyz.itbang.gspider.util.SpiderConfig;

/**
 * Created by yan on 2017/7/15.
 */
public class SimpleHandler extends AbstractHandler{
    @Override
    public Page handlePage(Page page) {
        System.out.println("Handle Page : "+page.getDocument().title());
        return page;
    }

    public static void main(String[] args) {
        Spider spider = new Spider();

        SpiderConfig config = spider.getConfig();
        config.seeds("http://luoyouzhijia.cn/");
        config.thread(5);
        config.maxFetch(10);
        config.handlers(new Class[]{SimpleHandler.class});

        spider.start();
    }
}
