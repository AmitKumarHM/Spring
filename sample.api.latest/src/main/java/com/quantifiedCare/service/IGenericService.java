package com.quantifiedCare.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;

/**
 * The Interface IGenericService has general method for CRUD operations on entities.
 *
 * @param <T>
 *            the generic type
 */
public interface IGenericService<T extends Serializable> {

    /**
     * Save entity.
     *
     * @param entity
     *            the entity
     * @return the t
     */
    T saveEntity(final T entity);

    /**
     * Save entities.
     *
     * @param entities
     *            the entities
     * @return the list
     */
    List<T> saveEntities(final List<T> entities);

    /**
     * Load all entity data.
     *
     * @return the list
     */
    List<T> loadAllEntityData();

    /**
     * Load all entity data corresponding to their given ids.
     *
     * @param ids
     *            the ids
     * @return the list
     */
    Iterable<T> loadAllEntityData(List<Long> ids);

    /**
     * Gets the entity by id.
     *
     * @param entityId
     *            the entity id
     * @return the entity by id
     */
    T getEntityById(Long entityId);

    /**
     * Load all entity data.
     *
     * @param predicate
     *            the predicate
     * @return the iterable
     */
    Iterable<T> loadAllEntityData(Predicate predicate);

    /**
     * Gets the list.
     *
     * @param pageable
     *            the pageable
     * @return the list
     */
    Page<T> getList(Pageable pageable);

    /**
     * Gets the list on the basis of sort object.
     *
     * @param sort
     *            the sort
     * @return the list
     */
    List<T> getList(Sort sort);

    /**
     * Delete entity by id.
     *
     * @param entityId
     *            the entity id
     */
    void deleteEntityById(Long entityId);

    /**
     * Delete entity.
     *
     * @param entity
     *            the entity
     */
    void deleteEntity(T entity);

    /**
     * Delete entities.
     *
     * @param entities
     *            the entities
     */
    void deleteEntities(Iterable<T> entities);

    /**
     * Gets the list of entities.
     *
     * @param pageable
     *            the pageable
     * @param predicate
     *            the predicate
     * @return the list
     */
    Page<T> getList(Pageable pageable, final Predicate predicate);

    /**
     * Gets the entity by predicate.
     *
     * @param predicate
     *            the predicate
     * @return the entity by predicate
     */
    T getEntityByPredicate(final Predicate predicate);

    /**
     * Gets the all.
     *
     * @param predicate the predicate
     * @param orderSpecifier the order specifier
     * @return the all
     */
    List<T> getAll(Predicate predicate,OrderSpecifier<?> orderSpecifier);
    
    /**
     * Delete all the entities with constraint mappings.
     *
     * @param entities the entities
     */
    void delete(Iterable<T> entities);
    
    /**
     * Gets the count of records filtered on the basis of passed predicate.
     *
     * @param predicate the predicate
     * @return the count
     */
    int getCount(Predicate predicate);
    
}
