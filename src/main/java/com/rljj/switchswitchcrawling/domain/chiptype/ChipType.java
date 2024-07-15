package com.rljj.switchswitchcrawling.domain.chiptype;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Getter @Setter
@Entity
@Table(name = "chip_type")
public class ChipType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String engName;

    @Column(nullable = false)
    private String imageName;

    private int price;

    @Column(nullable = false)
    @ColumnDefault("'nintendo'")
    private String consoleModel;

    private LocalDateTime crawled_date;
}
