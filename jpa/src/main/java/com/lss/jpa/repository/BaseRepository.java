package com.lss.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;


@NoRepositoryBean()
public interface BaseRepository<T>  extends JpaRepository<T, Integer>, JpaSpecificationExecutor<T> {

    @Query("select t from #{#entityName} t where t.id =:id")
    T findItemById(@Param("id") int id);

    @Modifying
    @Query("update #{#entityName} t set t.presenceStatus = 0 where t.id in :ids")
    void softDelete(@Param("ids")Integer[] ids);
}
