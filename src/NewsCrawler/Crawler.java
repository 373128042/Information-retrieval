package NewsCrawler;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import org.jsoup.nodes.Document;

public class Crawler extends BreadthCrawler{
	/**
     * @param crawlPath crawlPath is the path of the directory which maintains
     * information of this crawler
     * @param autoParse if autoParse is true,BreadthCrawler will auto extract
     * links which match regex rules from pag
     */
    public Crawler(String crawlPath, boolean autoParse) {
    super(crawlPath, autoParse);
    /*start page*/
    this.addSeed("http://cs.scut.edu.cn/xygk/xyxw/");//http://news.hfut.edu.cn/list-1-1.html

    /*fetch url like http://news.hfut.edu.cn/show-xxxxxxhtml*/
    this.addRegex("http://cs.scut.edu.cn/xygk/xyxw/.*xhtml");
    /*do not fetch jpg|png|gif*/
    this.addRegex("-.*\\.(jpg|png|gif).*");
    /*do not fetch url contains #*/
    this.addRegex("-.*#.*");
    }

    public void visit(Page page, CrawlDatums next) {
    String url = page.getUrl();
    /*if page is news page*/
    if (page.matchUrl("http://cs.scut.edu.cn/xygk/xyxw/.*xhtml")) {
        /*we use jsoup to parse page*/
        Document doc = page.getDoc();

        /*extract title and content of news by css selector*/
        String title = page.select("div[class=title]").first().text();
       // System.out.println("++++++++++++++title:\n" + title);
        String content = page.select("div[class=Text]").text();
        
        System.out.println("URL:\n" + url);
        System.out.println("title:\n" + title);
        System.out.println("content:\n" + content);

        /*If you want to add urls to crawl,add them to nextLink*/
        /*WebCollector automatically filters links that have been fetched before*/
        /*If autoParse is true and the link you add to nextLinks does not 
          match the regex rules,the link will also been filtered.*/
        //next.add("http://xxxxxx.com");
    }
    }

    public static void main(String[] args) throws Exception {
    Crawler crawler = new Crawler("crawl", true);
    crawler.setThreads(5);
    crawler.setTopN(1);
    //crawler.setResumable(true);
    /*start crawl with depth of 4*/
    crawler.start(2);
    }

}
