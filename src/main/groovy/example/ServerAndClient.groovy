package example

import xyz.itbang.gspider.Page
import xyz.itbang.gspider.Spider

/**
 * Created by yan on 2017/6/1.
 */

['server','client'].each {
    def roleName = it
    new Thread(new Runnable() {
        @Override
        void run() {
            Spider.crawl {
                role roleName
                seeds 'http://luoyouzhijia.cn'
                serviceUrl "http://localhost:8081/service"
                thread 1

                handle { Page page ->
                    println "Handle page ： ${page.document.title()}"
                }
            }
        }
    }).start()
}
