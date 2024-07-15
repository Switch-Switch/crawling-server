package com.rljj.switchswitchcrawling.domain.chiptype;

import com.rljj.switchswitchcrawling.domain.crawling.CrawledChip;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChipTypeService {

    private final ChipTypeRepository chipTypeRepository;

    @Transactional
    public void saveBulk(List<CrawledChip> chips) {
        chipTypeRepository.saveAll(chips.stream().map(ChipType::from).toList());
    }
}
