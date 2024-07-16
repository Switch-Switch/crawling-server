package com.rljj.switchswitchcrawling.domain.chiptype;

import com.rljj.switchswitchcrawling.domain.crawling.CrawledChip;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chip_type", indexes = {
        @Index(name = "idx_name", columnList = "name")
})
public class ChipType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String imageUrl;

    private String price;

    private String consoleModel;

    private LocalDateTime createdDate;

    public static ChipType from(CrawledChip chip) {
        return ChipType.builder()
                .name(chip.getName())
                .imageUrl(chip.getImageUrl())
                .price(chip.getPrice())
                .consoleModel(chip.getConsoleModel())
                .createdDate(LocalDateTime.now())
                .build();
    }
}
