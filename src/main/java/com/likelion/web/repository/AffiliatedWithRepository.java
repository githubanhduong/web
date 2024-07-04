package com.likelion.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.likelion.web.model.AffiliatedWith;

public interface AffiliatedWithRepository<AffiliatedWithId> extends JpaRepository<AffiliatedWith, AffiliatedWithId> {
}

