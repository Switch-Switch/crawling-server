package com.rljj.switchswitchcrawling.domain.chiptype;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class CrawledChip {
    private String name;
    private String imageName;
    private int price;
    private String consoleModel;
    private LocalDateTime crawling_date;
}
