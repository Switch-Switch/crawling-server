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
     * 스케줄링 메서드 <br/><br/>
     * 기본: 새벽 3시에 작업 시작
     */
//    바로 실행해보고 싶으면 fixedRate로 설정
//    @Scheduled(fixedRate = 1000000)
    @Scheduled(cron = "0 0 3 * * ?")
    public void crawl() {
        if (isFirst()) initialize();
        if (isOutdated()) update();
    }

    private boolean isFirst() {
        return chipTypeService.getCount() == 0;
    }

    private void initialize() {
        log.info("Starts the crawl initialization. URL: {}", url);
        try {
            int pageSize = getPageSize();

            for (int i = pageSize; i > 0; i--) { // 오래된 순부터
                List<CrawledChip> chips = crawlingRunner.crawl(url + "&p=" + i);
                chipTypeService.saveBulk(chips);
                sleepZZ(i);
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            log.info("Terminates the crawl initialization.");
        }
    }

    private boolean isOutdated() {
        try {
            return chipTypeService.getCount() < crawlingRunner.getTotalItemSize(url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void update() {
        log.info("Start the crawl update. URL: {}", url);
        try {
            int pageSize = getPageSize();

            for (int i = 1; i <= pageSize; i++) { // 최신 순부터
                List<CrawledChip> chips = crawlingRunner.crawl(url + "&p=" + i);
                for (CrawledChip chip : chips) {
                    if (isExist(chip.getName())) return;
                    chipTypeService.save(chip);
                }
                sleepZZ(i);
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            log.info("Terminates the crawl update.");
        }
    }

    private int getPageSize() throws IOException {
        int pageSize = crawlingRunner.getPageSize(url);
        log.info("pageSize: {}", pageSize);
        return pageSize;
    }

    private boolean isExist(String name) {
        return chipTypeService.isExist(name);
    }

    private void sleepZZ(int page) throws InterruptedException {
        log.info("Thread sleep, current page: {}", page);
        Thread.sleep(2000);
    }
}
