package com.felix.raspi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * Created by fsoewito on 2/20/2016.
 */

@NoRepositoryBean
public interface GenericRepository<T, ID extends Serializable> extends JpaSpecificationExecutor<T>, JpaRepository<T, ID> {
}
