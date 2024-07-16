package com.rljj.switchswitchcrawling.domain.chiptype;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChipTypeRepository extends JpaRepository<ChipType, Long> {
    Optional<ChipType> findByName(String name);
}
