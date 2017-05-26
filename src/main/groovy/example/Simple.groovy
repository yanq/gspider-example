package example

import xyz.itbang.gspider.Page
import xyz.itbang.gspider.Spider

/**
 * a simple example.
 * Created by yan on 2017/2/13.
 */

Spider.crawl {
    seeds "http://www.luoyouzhijia.cn"
    rounds 2
    maxFetch 20
    include ".*/audios/.*"

    handle { Page page ->
        println("Handle -> " + page.url)
        println("Title -> " + page.document.title())
    }
}