package com.lss.auth.repository;


import com.lss.auth.bean.Permission;
import com.lss.jpa.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends BaseRepository<Permission> {

}
