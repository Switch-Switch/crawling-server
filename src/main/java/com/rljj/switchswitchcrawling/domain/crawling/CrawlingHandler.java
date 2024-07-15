package com.rljj.switchswitchcrawling.domain.crawling;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CrawlingHandler {

    private final CrawlingRunner crawlingRunner;

}
