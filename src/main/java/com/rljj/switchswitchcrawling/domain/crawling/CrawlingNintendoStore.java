package com.rljj.switchswitchcrawling.domain.crawling;

import com.rljj.switchswitchcrawling.domain.chiptype.CrawledChip;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.List;
import java.util.Objects;


@Slf4j
public class CrawlingNintendoStore implements CrawlingRunner {

    /**
     * 크롤링 후 CrawledChip 객체로 변환
     *
     * @param url 페이지 파라미터가 포함된 url
     * @return 칩 데이터 DTO
     * @throws IOException 크롤링 실패
     */
    @Override
    public List<CrawledChip> crawl(String url) throws IOException {
        Document document = Jsoup.connect(url).get();
        System.out.println(document);

        return List.of();
    }

    /**
     * 전체 페이지 사이즈 가져오는 메서드
     *
     * @return 페이지 사이즈
     * @throws IOException 크롤링 실패
     */
    @Override
    public int getPageSize(String url) throws IOException {
        Document document = Jsoup.connect(url).get();
        Element element = document.getElementById("toolbar-amount");
        return Integer.parseInt(Objects.requireNonNull(element).text().split(" ")[2]);
    }
}