package xyz.itbang.gspider

import groovy.xml.XmlUtil

/**
 * Created by yan on 2017/2/14.
 */
class PageTest extends GroovyTestCase {
    String htmlText = '''
                        <list>
                            <technology>
                                <name>Hello,Page!</name>
                            </technology>
                        </list>
                     '''
    String jsonText = '{ "name": "John Doe" }'
    String url = "http://www.luofans.com/aboutUs"

    @Override
    void tearDown() {
        super.tearDown();
    }

    @Override
    void setUp() {
        super.setUp()

    }

    void testGetHtmlFromHtml() {
        Page page = new Page()
        page.text = htmlText
        println XmlUtil.serialize(page.html)
        assert page.html.technology.name.text() == "Hello,Page!"
    }
    void testGetHtmlFromJson() {
        Page page = new Page()
        page.text = jsonText
        println XmlUtil.serialize(page.html)
        assert page.html.text().contains(jsonText)
    }
    void testGetJsonFromJson() {
        Page page = new Page()
        page.text = jsonText
        println page.json
        assert page.json.name == "John Doe"
    }
    void testGetJsonFromHtml() {
        Page page = new Page()
        page.text = htmlText
//        println page.json // 当有异常的时候，异常处理似乎是另一个线程来处理的。多次使用json，会初始化多次，大概异常处理代码尚未生效。而上面的测试，没有异常，多次使用，是不会多次初始化的。
        def json = page.json
        assert json.toString() == "{}"
    }

    void testGetURI(){
        Page page = new Page()
        page.url = url
        println(page.uri.toString())

        def a = 2
        println(a=5) //验证一下这种模式的返回结果

        assert page.uri instanceof URI
    }

    void testURI(){
        //测试
        def link = "http://www.luofans.com/f s"
        def path = "/s"
        URL u = new URL(link)
//        URL u2 = new URL(path) 没有协议不可以


//        URI uri = new URI(link) 空格不可以
        URI uri2 = new URI(path)

//        println(u.toURI()) 无法转换

        URI i = new URI("http://foo.com/hello%20world")
        def it = i.toURL()
//        URI i2 = new URI("http://foo.com/hello world") 这里不可以，格式不对
//        def i2t = i2.toURL()

        println("over")
    }
}
