package example

import xyz.itbang.gspider.Page
import xyz.itbang.gspider.Spider
import xyz.itbang.gspider.remote.HessianServerScheduler
import xyz.itbang.gspider.scheduler.LocalScheduler

/**
 * a simple example.
 * Created by yan on 2017/2/13.
 */

Spider.crawl {
    seeds "http://www.luoyouzhijia.cn"
    rounds 2
    maxFetch 20
    include ".*/audios/.*"
    scheduler(LocalScheduler)

    handle { Page page ->
        println("Handle -> " + page.url)
        println("Title -> " + page.document.title())
    }
}