package example

import org.openqa.selenium.chrome.ChromeDriver
import xyz.itbang.gspider.Page
import xyz.itbang.gspider.Spider

/**
 * 多浏览器窗口，chrome，每线程一个共享的浏览器窗口。
 * 因为比包内不支持外部方法调用，只能是自己定义的，所以，取巧了静态方法。
 * Created by yan on 2017/2/20.
 */
class MultiChromeSpider {
   static ThreadLocal<ChromeDriver> local = new ThreadLocal<>()

    public static void main(String[] args) {
        System.setProperty ( "webdriver.chrome.driver" , "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe" );
        Spider.crawl {
            seeds "http://www.oschina.net/"
            rounds 2
            maxFetch 30
            thread 4

            download { url ->
                ChromeDriver driver = MultiChromeSpider.getDriver()
                driver.get(url)
                def result = driver.getPageSource()
                return result
            }

            handle{ Page page ->
                println("Handle -> "+page.url)
//        println("Text -> "+page.text)
                println("Title -> "+page.html.head.title)
            }
        }

    }

    static ChromeDriver getDriver(){
        ChromeDriver driver = local.get()
        if (!driver){
            driver = new ChromeDriver()
            local.set(driver)
        }
        return driver
    }
}
