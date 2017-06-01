package remote

import xyz.itbang.gspider.Page
import xyz.itbang.gspider.Spider

/**
 * Created by yan on 2017/6/1.
 */

['client', 'server'].each {
    def roleName = it
    new Thread(new Runnable() {
        @Override
        void run() {
            Spider.crawl {
                role roleName
                seeds 'http://luoyouzhijia.cn'
                serviceUrl "http://localhost:8081/service"

                handle { Page page ->
                    println "Handler page ： ${page.document.title()}"
                }
            }
        }
    }).start()
}
