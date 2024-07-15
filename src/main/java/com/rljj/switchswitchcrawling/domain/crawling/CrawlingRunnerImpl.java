package com.rljj.switchswitchcrawling.domain.crawling;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CrawlingRunnerImpl implements CrawlingRunner {

    @Value("${crawling.url}")
    public String url;

    @Override
    public void crawl() {

    }
}