package com.rljj.switchswitchcrawling.domain.chiptype;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChipRepository extends JpaRepository<Chip, Long> {
    Optional<Chip> findByName(String name);
}
