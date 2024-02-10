package com.example.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.billing.model.TvChannelsEntity;

public interface TvChannelRepository extends JpaRepository<TvChannelsEntity, Long> {

}
