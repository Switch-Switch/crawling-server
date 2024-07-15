package com.rljj.switchswitchcrawling.domain.crawling;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class CrawlingHandler {

    private final CrawlingRunner crawlingRunner;

    @Value("${crawling.url}")
    private String url;

    @Scheduled(fixedRate = 1000000)
    public void crawl() {
        try {
            log.info("Crawling start, URL: {}", url);
            int pageSize = crawlingRunner.getPageSize(url);

            for (int i = 1; i <= pageSize; i++) {
                crawlingRunner.crawl(url + "&p=" + i);
            }

        } catch (IOException e) {
            log.error("Crawling error", e);
            throw new RuntimeException(e);
        } finally {
            log.info("Crawling end");
        }
    }
}
