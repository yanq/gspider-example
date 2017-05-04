import groovy.util.logging.Slf4j
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

import java.util.concurrent.Callable
import java.util.concurrent.Executors

/**
 * a proxy hunter
 * 代理猎手，查找代理。
 * Created by yan on 2017/5/4 0004.
 */
@Slf4j
class ProxyHunter {

    //从西刺代理抓取代理列表
    static List findProxyListFromXiCi() {
        def xici = "http://www.xicidaili.com/nn"
        log.info("find proxy from $xici")

        def result = []
        def urls = [xici]
        (2..100).each {urls << xici+"/"+it}

//        println(urls)

        urls.each {
            Document doc = Jsoup.connect(it).get();
            Elements proxies = doc.select("tr");
            proxies.each {
                def proxy = it.text().split()[0, 1]
                log.debug "found proxy : $proxy"
                result << proxy
            }
        }


        log.info("find ${result.size()} proxy ")

        return result
    }

    //验证代理可用性
    static boolean checkProxy(List proxy) {
        try {
            def result = Jsoup.connect('http://ip.chinaz.com/getip.aspx').timeout(10000).proxy(proxy[0], Integer.parseInt(proxy[1])).execute().body()
            log.debug "check proxy : $proxy ,result : $result"
            return result.contains('address') ? true : false
        } catch (Exception e) {
            log.debug "check proxy : $proxy ,exception：$e.message"
            return false
        }
    }

    //检查代理列表，找出所有可用的代理
    static List checkProxyList(List list) {
        List result = []

        log.debug("check proxy list from $list")

        def tasks = list.collect {
            new Callable<Object>() {
                @Override
                Object call() throws Exception {
                    if (checkProxy(it)) result << it
                }
            }
        }

        def services = Executors.newCachedThreadPool()
        services.invokeAll(tasks)
        services.shutdown()

        log.info("find out available ${result.size()} proxy")

        return result
    }

    public static void main(String[] args) {

        log.info("Hunting proxy ------")

        def oList = findProxyListFromXiCi()

        def list = oList
        5.times {
            list = checkProxyList(list)
        }
    }
}
