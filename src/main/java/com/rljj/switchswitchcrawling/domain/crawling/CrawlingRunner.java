package com.rljj.switchswitchcrawling.domain.crawling;

import com.rljj.switchswitchcrawling.domain.chiptype.CrawledChip;

import java.io.IOException;
import java.util.List;

public interface CrawlingRunner {
    List<CrawledChip> crawl(String url) throws IOException;

    int getPageSize(String url) throws IOException;
}
