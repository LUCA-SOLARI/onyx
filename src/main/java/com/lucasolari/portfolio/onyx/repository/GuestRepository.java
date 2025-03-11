package com.lucasolari.portfolio.onyx.repository;

import com.lucasolari.portfolio.onyx.domain.persistence.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest,Long> {}
