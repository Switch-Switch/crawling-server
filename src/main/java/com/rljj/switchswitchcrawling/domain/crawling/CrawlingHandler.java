package com.rljj.switchswitchcrawling.domain.crawling;

import com.rljj.switchswitchcrawling.domain.chiptype.ChipTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class CrawlingHandler {

    private final CrawlingRunner crawlingRunner;
    private final ChipTypeService chipTypeService;

    @Value("${crawling.url}")
    private String url;

    /**
     * 스케줄링 메서드 <br />
     * 기본: 새벽 3시에 작업 시작 <br />
     * 바로 실행해보고 싶으면 @Scheduled(fixedRate = 1000000)로 설정
     */
    @Scheduled(cron = "0 0 3 * * ?")
    public void crawl() {
        log.info("Crawling start, URL: {}", url);
        try {
            int pageSize = crawlingRunner.getPageSize(url);
            log.info("pageSize: {}", pageSize);

            for (int i = 1; i <= pageSize; i++) {
                List<CrawledChip> chips = crawlingRunner.crawl(url + "&p=" + i);
                chipTypeService.saveBulk(chips);
                sleepZZ(i);
            }
        } catch (IOException e) {
            log.error("Crawling error", e);
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            log.error("Thread sleep error", e);
            throw new RuntimeException(e);
        } finally {
            log.info("Crawling end");
        }
    }

    private void sleepZZ(int page) throws InterruptedException {
        log.info("Thread sleep, current page: {}", page);
        Thread.sleep(2000);
    }
}
