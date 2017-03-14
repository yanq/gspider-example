package example

import geb.Browser
import xyz.itbang.gspider.Page
import xyz.itbang.gspider.Spider

/**
 * 登录后抓取内容
 * Created by yan on 2017/3/14.
 */
System.setProperty ( "webdriver.chrome.driver" , "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe" );

String url = "http://www.luofans.com/"

//to login
Browser browser = new Browser()
browser.go url
Thread.sleep(10000)
def cookies = browser.driver.manage().cookies
browser.close()

def headers = [requestProperties:['Cookie':cookies.collect { "${it.name}=${it.value}" }.join("; "),'User-Agent':"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:51.0) Gecko/20100101 Firefox/51.0"]]

Spider.crawl {
    seeds url
    rounds 2
    maxFetch 10
    defaultParameters headers
    //include ".*/audios/.*"



    handle{ Page page ->
//        println("Handle -> "+page.url)
        println("Title -> "+page.html.head.title)
        println("Text -> "+page.text)
    }
}
