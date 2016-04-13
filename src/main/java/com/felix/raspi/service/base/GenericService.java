package com.felix.raspi.service.base;

import com.felix.raspi.repository.GenericRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

/**
 * Created by fsoewito on 2/20/2016.
 */
public class GenericService<T extends Object, ID extends Serializable> {
    protected final GenericRepository<T, ID> repository;
    @PersistenceContext
    protected EntityManager entityManager;


    public GenericService(GenericRepository<T, ID> repository) {
        this.repository = repository;
    }

    public Object getIdentifier(Object entity) {
        return entityManager.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(entity);
    }

    public List<T> findAll() {
        return repository.findAll();
    }

    public List<T> find(Specification<T> specification) {
        return repository.findAll(specification);
    }

    public Page<T> find(Specification<T> specification, Pageable pageable) {
        return repository.findAll(specification, pageable);
    }

    public T findOne(ID id) {
        T result = repository.findOne(id);
        if (result == null) {
            throw new RuntimeException("Entity by id " + id + " is not found!");
        }
        return result;
    }

    @Transactional
    public <S extends T> S create(S entity) {
        return repository.save(entity);
    }

    @Transactional
    public <S extends T> S update(S entity, ID id) {
        if (!repository.exists(id)) {
            throw new RuntimeException("Entity by id " + id + " is not found!");
        }
        return repository.save(entity);
    }

    @Transactional
    public void delete(ID id) {
        if (!repository.exists(id)) {
            throw new RuntimeException("Entity by id " + id + " is not found!");
        }
        repository.delete(id);
    }
}
