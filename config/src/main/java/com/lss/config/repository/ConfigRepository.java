package com.lss.config.repository;

import com.lss.config.bean.Config;
import com.lss.jpa.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ConfigRepository extends BaseRepository<Config> {

    @Query("select item from Config  item where name = :name")
    Config  findByName(@Param("name") String name);
}
