package example

import geb.Browser
import org.openqa.selenium.chrome.ChromeDriver
import xyz.itbang.gspider.Page
import xyz.itbang.gspider.Spider

/**
 * 使用 Geb 作为抓取工具，用于复杂条件下的抓取。
 * Created by yan on 2017/2/20.
 */
System.setProperty ( "webdriver.chrome.driver" , "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe" );

Spider.crawl {
    seeds "http://www.oschina.net/"
    rounds 2
    maxFetch 10
    //include ".*/audios/.*"

    download { url ->
        Browser browser = new Browser()  //无论在这里还是外面，打开的都是一个浏览器，在调用时是同步的
        browser.go url
        browser.getDriver().getPageSource()
    }

    handle{ Page page ->
        println("Handle -> "+page.url)
//        println("Text -> "+page.text)
        println("Title -> "+page.html.head.title)
    }
}


