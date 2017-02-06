package com.quantifiedCare.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;
import com.quantifiedCare.core.dao.IGenericDAO;
import com.quantifiedCare.core.entity.AbstractBaseEntity;
import com.quantifiedCare.util.common.QuantifiedCareUtil;

/**
 * The Class AbstractService.The purpose of this service class is to provide the implementation of common methods like
 * load, get, save etc. This service is extends by all the business entity specific service class.
 * 
 * @param <T>
 *            * the generic type
 */
public abstract class AbstractService<T extends AbstractBaseEntity> {

    /** Fully Qualified Class Name. */
    private static final String FQCN = AbstractService.class.getName();


    /** The common dao. */
    private IGenericDAO<T> baseDAO;

    /**
     * Save entity.
     * 
     * @param entity
     *            the entity--null check has been performed
     * @return the t
     */
    @Transactional
    public T saveEntity(final T entity) {

        T savedEntity = null;

        try {
            if (null != entity) {
                savedEntity = this.baseDAO.save(entity);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("saveEntity", e);
        }

        return savedEntity;
    }

    /**
     * Gets the entity by predicate.
     * 
     * @param predicate
     *            the predicate
     * @return the entity by predicate
     */
    public T getEntityByPredicate(final Predicate predicate) {

        T entityFound = null;

        try {
            entityFound = this.baseDAO.getEntityByPredicate(predicate);
        } catch (RuntimeException e) {
            throw new RuntimeException("getEntityByPredicate", e);
        }

        return entityFound;
    }

    /**
     * Save entities.
     * 
     * @param entities
     *            the entities--null check has been performed
     * @return the list
     */
    @Transactional
    public List<T> saveEntities(final List<T> entities) {

        List<T> savedEntities = null;

        try {
            if (null != entities) {
                savedEntities = this.baseDAO.save(entities);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException( "saveEntity", e);
        }

        return savedEntities;
    }

    /**
     * Load all entity data.
     * 
     * @return the list
     */
    @Transactional(readOnly = true)
    public List<T> loadAllEntityData() {

        List<T> entities = null;

        try {
            entities = this.baseDAO.getAll();
        } catch (RuntimeException e) {
            throw new RuntimeException("loadAllEntityData", e);
        }

        return entities;
    }

    /**
     * Load all entity corresponding to given ids.
     * 
     * @param ids
     *            the ids--null check has been performed
     * @return the list
     */
    @Transactional(readOnly = true)
    public Iterable<T> loadAllEntityData(final List<Long> ids) {

        Iterable<T> entities = null;

        try {
            if (null != ids) {
                entities = this.baseDAO.getAll(ids);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("loadAllEntityData", e);
        }

        return entities;
    }

    /**
     * Gets the entity by id.
     * 
     * @param entityId
     *            the entity id--null check has been performed
     * @return the entity by id
     */
    @Transactional(readOnly = true)
    public T getEntityById(final Long entityId) {

        T savedEntity = null;

        try {
            if (null != entityId) {
                savedEntity = this.baseDAO.getById(entityId);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("getEntityById", e);
        }

        return savedEntity;
    }

    /**
     * Load all entity data.
     * 
     * @param predicate
     *            the predicate
     * @return the iterable
     */
    @Transactional(readOnly = true)
    public Iterable<T> loadAllEntityData(final Predicate predicate) {

        Iterable<T> entities = null;

        try {
            entities = this.baseDAO.getAll(predicate);
        } catch (RuntimeException e) {
            throw new RuntimeException("loadAllEntityData", e);
        }

        return entities;
    }

    /**
     * Gets the list.
     * 
     * @param pageable
     *            the pageable
     * @return the list
     */
    @Transactional(readOnly = true)
    public Page<T> getList(final Pageable pageable) {

        Page<T> entities = null;

        try {
            if (QuantifiedCareUtil.isAllDataRquired(pageable.getPageSize())) {
                int count = this.getCount(null);
                if (count > 0) {
                    PageRequest pageRequest = new PageRequest(pageable.getPageNumber(), count, pageable.getSort());
                    entities = this.baseDAO.getAll(pageRequest);
                } else {
                    entities = this.baseDAO.getAll(pageable);
                }
            } else {
                entities = this.baseDAO.getAll(pageable);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("getList", e);
        }

        return entities;
    }

    /**
     * Gets the list of the basis of sort object.
     * 
     * @param sort
     *            the sort
     * @return the list
     */
    @Transactional(readOnly = true)
    public List<T> getList(final Sort sort) {

        List<T> entities = null;

        try {
            entities = this.baseDAO.getAll(sort);
        } catch (RuntimeException e) {
            throw new RuntimeException("getList", e);
        }

        return entities;
    }

    /**
     * Gets the list of entities.
     * 
     * @param pageable
     *            the pageable
     * @param predicate
     *            the predicate
     * @return the list
     */
    @Transactional(readOnly = true)
    public Page<T> getList(Pageable pageable, final Predicate predicate) {

        Page<T> entities = null;

        try {
            if (QuantifiedCareUtil.isAllDataRquired(pageable.getPageSize())) {
                int count = this.getCount(predicate);
                if (count > 0) {
                    PageRequest pageRequest = new PageRequest(pageable.getPageNumber(), count, pageable.getSort());
                    entities = this.baseDAO.getAll(pageRequest, predicate);
                } else {
                    entities = this.baseDAO.getAll(pageable, predicate);
                }
            } else {
                entities = this.baseDAO.getAll(pageable, predicate);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("getList", e);
        }

        return entities;
    }

    /**
     * Delete entity by id.
     * 
     * @param entityId
     *            the entity id--null check has been performed
     */
    @Transactional
    public void deleteEntityById(final Long entityId) {

        try {
            if (null != entityId) {
                this.baseDAO.delete(entityId);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("deleteEntityById", e);
        }

    }

    /**
     * Delete entity.
     * 
     * @param entity
     *            the entity--null check has been performed
     */
    @Transactional
    public void deleteEntity(final T entity) {

        try {
            if (null != entity) {
                this.baseDAO.delete(entity);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("deleteEntity", e);
        }

    }

    /**
     * Delete entities.
     * 
     * @param entities
     *            the entities--null check has been performed
     */
    @Transactional
    public void deleteEntities(final Iterable<T> entities) {

        try {
            if (null != entities) {
                this.baseDAO.delete(entities);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("deleteEntities", e);
        }
    }

    /**
     * Gets list of entity on the basis of predicate and order specifiers.
     * 
     * @param predicate
     *            the predicate
     * @param orderSpecifier
     *            the order specifier
     * @return the all
     */
    public List<T> getAll(Predicate predicate, OrderSpecifier<?> orderSpecifier) {

        List<T> entityList = null;

        try {
            entityList = this.baseDAO.getAll(predicate, orderSpecifier);
        } catch (RuntimeException e) {
            throw new RuntimeException( "getAll", e);
        }

        return entityList;
    }

    /**
     * Delete entities.
     * 
     * @param entities
     *            the entities--null check has been performed
     */
    public void delete(Iterable<T> entities) {

        try {
            if (null != entities) {
                this.baseDAO.deleteEntities(entities);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException( "delete", e);
        }

    }

    /**
     * Gets the count of records filtered on the basis of passed predicate.
     * 
     * @param predicate
     *            the predicate
     * @return the count
     */
    public int getCount(Predicate predicate) {

        try {
            return Integer.valueOf(String.valueOf(this.baseDAO.getCount(predicate)));
        } catch (RuntimeException e) {
            throw new RuntimeException( "getCount", e);
        }

    }

    /**
     * Sets the generic dao.
     */
    @PostConstruct
    protected abstract void setGenericDAO();

    /**
     * Sets the common dao.
     * 
     * @param baseDAO
     *            the new common dao
     */
    public void setBaseDAO(final IGenericDAO<T> baseDAO) {
        this.baseDAO = baseDAO;
    }
}
