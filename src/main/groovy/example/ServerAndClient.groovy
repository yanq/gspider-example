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
                seeds 'http://luoyouzhijia.cn/'
                serviceUrl "http://localhost:8087/service"
                thread 2
                maxFetch 1000
                maxClientWaiting(10000)

                handle { Page page ->
                    println "Handle page ： ${page}"
                }

                review { Page page ->
                    println "Page title ： ${page.document.title()}"
                }
            }
        }
    }).start()
}
