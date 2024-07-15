package com.rljj.switchswitchcrawling.domain.crawling;

import com.rljj.switchswitchcrawling.domain.chiptype.CrawledChip;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.List;

/**
 * 크롤링 후 CrawledChip 객체 반환
 */

@Slf4j
public class CrawlingNintendoStore implements CrawlingRunner {

    @Override
    public List<CrawledChip> crawl(String url) throws IOException {
        Document document = Jsoup.connect(url).get();
        System.out.println(document);

        return List.of();
    }

    @Override
    public int getPageSize(String url) throws IOException {
        Document document = Jsoup.connect(url).get();
        Element element = document.getElementById("toolbar-amount");

        assert element != null;

        return Integer.parseInt(element.text().split(" ")[2]);
    }
}