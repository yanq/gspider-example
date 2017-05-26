package example

import geb.Browser
import org.openqa.selenium.chrome.ChromeDriver
import xyz.itbang.gspider.Page
import xyz.itbang.gspider.Spider

/**
 * 使用 Geb 作为抓取工具，用于复杂条件下的抓取。
 * Created by yan on 2017/2/20.
 */
System.setProperty ( "webdriver.chrome.driver" , "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver227.exe" );

Spider.crawl {
    seeds "http://www.oschina.net/"
    rounds 1
    maxFetch 1

    handle{ Page page ->
        println("Handle -> "+page.url)

        Browser browser = new Browser()
        browser.go page.url
        browser.js."scroll(0,9000)"
        Thread.sleep(5000)
        page.text = browser.getDriver().getPageSource()

        println("Title -> "+page.document.title())
    }
}


