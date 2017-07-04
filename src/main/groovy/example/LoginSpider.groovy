package example

import geb.Browser
import xyz.itbang.gspider.Page
import xyz.itbang.gspider.Spider
import xyz.itbang.gspider.util.Tools

/**
 * 登录后抓取内容
 * Created by yan on 2017/3/14.
 */
System.setProperty ( "webdriver.chrome.driver" , "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver227.exe" );

String url = "http://luoyouzhijia.cn/"

//to login
Browser browser = new Browser()
browser.go url
Tools.waitFor("登录")
def cookies = browser.driver.manage().cookies
browser.close()

Spider.crawl {
    seeds url
    rounds 2
    maxFetch 10

    handle{ Page page ->
        println("Handle -> "+page.url)
        page.connection.header('Cookie',cookies.collect { "${it.name}=${it.value}" }.join("; "))
        println("Title -> "+page.document.title())
    }
}
