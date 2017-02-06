package com.quantifiedCare.core.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.PostConstruct;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;
import com.quantifiedCare.core.jpa.GenericJPARepository;
import com.quantifiedCare.util.common.QuantifiedCareUtil;

/**
 * The Class AbstractDAO.
 * <p>
 * There are two basic purpose for this class 1. Wrapping Runtime {@link DataAccessException} for Spring Data JPA
 * repository method to application checked {@linkplain RuntimeException} 2. Providing DAO layer specific profiling
 * information
 * </p>
 * 
 * @param <T>
 *            the generic type
 */
public abstract class AbstractDAO<T extends Serializable> {

    /** The generic jpa repository. */
    private GenericJPARepository<T> baseRepository;

    /**
     * Gets the by id.
     * 
     * @param entityId
     *            the entity id--null check has been performed
     * @return the by id
     * @throws RuntimeException
     *             the RuntimeException
     */
    public T getById(final Long entityId) throws RuntimeException {

        T entity = null;

        try {
            if (null != entityId) {
                entity = this.baseRepository.findOne(entityId);
            }
        } catch (DataAccessException e) {
            throw new RuntimeException( "getById", e);
        }

        return entity;
    }

    /**
     * Gets the all.
     * 
     * @return the all
     * @throws RuntimeException
     *             the RuntimeException
     */
    public List<T> getAll() throws RuntimeException {

        List<T> entities = new ArrayList<T>();

        try {
            entities = this.baseRepository.findAll();
        } catch (DataAccessException e) {
            throw new RuntimeException("getAll", e);
        }

        return entities;
    }

    /**
     * Gets the all by ids.
     * 
     * @param ids
     *            the ids--null check has been performed
     * @return the all by ids
     * @throws RuntimeException
     *             the RuntimeException
     */
    public Iterable<T> getAll(final Collection<Long> ids) throws RuntimeException {

        Iterable<T> entities = null;

        try {
            if (null != ids) {
                entities = this.baseRepository.findAll(ids);
            }
        } catch (DataAccessException e) {
            throw new RuntimeException("getAll", e);
        }

        return entities;
    }

    /**
     * Gets the all entities.
     * 
     * @param pageable
     *            the pageable
     * @param predicate
     *            the predicate
     * @return the all
     * @throws RuntimeException
     *             the RuntimeException
     */
    public Page<T> getAll(final Pageable pageable, final Predicate predicate) throws RuntimeException {
        Pageable newPageable = pageable;
        if (null != pageable) {
            if (QuantifiedCareUtil.isAllDataRquired(pageable.getPageSize())) {
                long count = this.getCount(predicate);
                if (count > 0) {
                    newPageable = new PageRequest(pageable.getPageNumber(), Integer.valueOf(String.valueOf(count)),
                        pageable.getSort());
                }
            }
        }
        Page<T> entities = null;
        try {
            entities = this.baseRepository.findAll(predicate, newPageable);
        } catch (DataAccessException e) {
            throw new RuntimeException("getAll", e);
        }

        return entities;
    }

    /**
     * Gets the all entities list using given predicate.
     * 
     * @param predicate
     *            the predicate
     * @return the all
     * @throws RuntimeException
     *             the RuntimeException
     */
    public Iterable<T> getAll(final Predicate predicate) throws RuntimeException {

        Iterable<T> entities = null;

        try {
            entities = this.baseRepository.findAll(predicate);
        } catch (DataAccessException e) {
            throw new RuntimeException("getAll", e);
        }

        return entities;
    }

    /**
     * Gets the all entities.
     * 
     * @param pageable
     *            the pageable
     * @return the all
     * @throws RuntimeException
     *             the RuntimeException
     */
    public Page<T> getAll(final Pageable pageable) throws RuntimeException {

    	Page<T> entities = null;

        try {
            entities = this.baseRepository.findAll(pageable);
        } catch (DataAccessException e) {
            throw new RuntimeException("getAll", e);
        }

        return entities;
    }

    /**
     * Gets the all.
     * 
     * @param sort
     *            the sort
     * @return the all
     * @throws RuntimeException
     *             the RuntimeException
     */
    public List<T> getAll(final Sort sort) throws RuntimeException {

        List<T> entityList = null;

        try {
            entityList = this.baseRepository.findAll(sort);
        } catch (DataAccessException e) {
            throw new RuntimeException( "getAll", e);
        }

        return entityList;
    }

    /**
     * Save entity.
     * 
     * @param entity
     *            the entity--null check has been performed
     * @return the t
     * @throws RuntimeException
     *             the RuntimeException
     */
    public T save(final T entity) throws RuntimeException {

        T savedEntity = null;

        try {
            if (null != entity) {
                savedEntity = this.baseRepository.save(entity);
            }
        } catch (DataAccessException e) {
            throw new RuntimeException("save", e);
        }

        return savedEntity;
    }

    /**
     * Save all.
     * 
     * @param entities
     *            the entities--null check has been performed
     * @return the list
     * @throws RuntimeException
     *             the RuntimeException
     */
    public List<T> save(final Iterable<T> entities) throws RuntimeException {

        List<T> savedEntities = null;

        try {
            if (null != entities) {
                savedEntities = this.baseRepository.save(entities);
            }
        } catch (DataAccessException e) {
            throw new RuntimeException("save", e);
        }

        return savedEntities;
    }

    /**
     * Delete entity.
     * 
     * @param entityId
     *            the entity id--null check has been performed
     * @throws RuntimeException
     *             the RuntimeException
     */
    public void delete(final Long entityId) throws RuntimeException {

        try {
            if (null != entityId) {
                T entity = this.getById(entityId);
                if (null != entity) {
                    this.baseRepository.delete(entity);
                }
            }
        } catch (DataAccessException e) {
            throw new RuntimeException("delete", e);
        }
    }

    /**
     * Delete entity.
     * 
     * @param entity
     *            the entity--null check has been performed
     * @throws RuntimeException
     *             the RuntimeException
     */
    public void delete(final T entity) throws RuntimeException {

        try {
            if (null != entity) {
                this.baseRepository.delete(entity);
            }
        } catch (DataAccessException e) {
            throw new RuntimeException( "delete", e);
        }
    }

    /**
     * Delete entities.
     * 
     * @param entities
     *            the entities--null check has been performed
     * @throws RuntimeException
     *             the RuntimeException
     */
    public void delete(final Iterable<T> entities) throws RuntimeException {

        try {
            if (null != entities) {
                this.baseRepository.deleteInBatch(entities);
            }
        } catch (DataAccessException e) {
            throw new RuntimeException("delete", e);
        }
    }

    /**
     * Delete all the entities in batch from the table.
     * 
     * @throws RuntimeException
     *             the RuntimeException
     */
    public void deleteAll() throws RuntimeException {

        try {
            this.baseRepository.deleteAllInBatch();
        } catch (DataAccessException e) {
            throw new RuntimeException("deleteAll", e);
        }
    }

    /**
     * Gets the entity by predicate.
     * 
     * @param predicate
     *            the predicate
     * @return the entity by predicate
     * @throws RuntimeException
     *             the RuntimeException
     */
    public T getEntityByPredicate(final Predicate predicate) throws RuntimeException {

        T entity = null;

        try {
            entity = this.baseRepository.findOne(predicate);
        } catch (DataAccessException e) {
            throw new RuntimeException( "getEntityByPredicate",  e);
        }

        return entity;
    }

    /**
     * Gets list of entity on the basis of predicate and order specifiers
     * 
     * @param predicate
     *            the predicate
     * @param orderSpecifier
     *            the order specifier
     * @return the all
     * @throws RuntimeException
     *             the RuntimeException
     */
    public List<T> getAll(Predicate predicate, OrderSpecifier<?> orderSpecifier) throws RuntimeException {

        List<T> entityList = null;

        try {
            entityList = (List<T>) this.baseRepository.findAll(predicate, orderSpecifier);
        } catch (DataAccessException e) {
            throw new RuntimeException( "getAll", e);
        }

        return entityList;
    }

    /**
     * Delete entities.
     * 
     * @param entities
     *            the entities--null check has been performed
     * @throws RuntimeException
     *             the RuntimeException
     */
    public void deleteEntities(Iterable<T> entities) throws RuntimeException {

        try {
            if (null != entities) {
                this.baseRepository.delete(entities);
            }
        } catch (DataAccessException e) {
            throw new RuntimeException("deleteEntities", e);
        }
    }

    /**
     * Gets the count of records filtered on the basis of passed predicate.
     * 
     * @param predicate
     *            the predicate
     * @return the count
     * @throws RuntimeException
     *             the RuntimeException
     */
    public Long getCount(Predicate predicate) throws RuntimeException {

        Long count = null;

        try {
            count = this.baseRepository.count(predicate);
        } catch (DataAccessException e) {
            throw new RuntimeException("getCount", e);
        }

        return count;
    }

    /**
     * Execute query.
     * 
     * @param jdbcTemplate
     *            the jdbc template
     * @param queryString
     *            the query string
     * @throws RuntimeException
     *             the RuntimeException
     */
    public void executeQuery(JdbcTemplate jdbcTemplate, @Nonnull String queryString) throws RuntimeException {

        try {

            jdbcTemplate.execute(queryString);

        } catch (DataAccessException e) {
            throw new RuntimeException( "executeQuery", e);
        }
    }

    /**
     * This API is called after the initialization of the bean. In this API every DAO layer will set the reference of
     * this own repository and that reference will in turn call these general CRUD operations Sets the base JPA
     * repository.
     * 
     */
    @PostConstruct
    protected abstract void setBaseJPARepository();

    /**
     * Sets the generic jpa repository.
     * 
     * @param baseRepository
     *            the baseRepository to set
     */
    protected void setBaseRepository(final GenericJPARepository<T> baseRepository) {
        this.baseRepository = baseRepository;
    }

    
}
