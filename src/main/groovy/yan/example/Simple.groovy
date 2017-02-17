package yan.example

import yan.util.crawl.Page
import yan.util.crawl.Spider

/**
 * a simple example.
 * Created by yan on 2017/2/13.
 */

Spider.crawl {
    seed "http://www.luofans.com"
    rounds 2
    maxFetch 20
    include ".*/audios/.*"

    handle{ Page page ->
        println("Handle -> "+page.url)
        println("Title -> "+page.html.head.title)
    }
}