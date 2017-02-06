package com.quantifiedCare.core.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.annotation.Nonnull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;

/**
 * The Interface IGenericDAO has general method for CRUD operations on entities.
 *
 * @param <T>
 *            the generic type
 */
public interface IGenericDAO<T extends Serializable> {

    /**
     * Get entity the by its id.
     *
     * @param entityId
     *            the entity id
     * @return the by id
     * @throws RuntimeException
     *             the RuntimeException
     */
    T getById(Long entityId) throws RuntimeException;

    /**
     * Gets the all persisted entities.
     *
     * @return the all
     * @throws RuntimeException
     *             the RuntimeException
     */
    List<T> getAll() throws RuntimeException;

    /**
     * Gets the all entities corresponding to there ids.
     *
     * @param ids
     *            the ids
     * @return the all
     * @throws RuntimeException
     *             the RuntimeException
     */
    Iterable<T> getAll(Collection<Long> ids) throws RuntimeException;

    /**
     * Gell all basis on some predicate.
     *
     * @param predicate
     *            the predicate
     * @return the list
     * @throws RuntimeException
     *             the RuntimeException
     */
    Iterable<T> getAll(Predicate predicate) throws RuntimeException;

    /**
     * Get the all entities page wise.
     *
     * @param pageable
     *            the pageable
     * @return the all
     * @throws RuntimeException
     *             the RuntimeException
     */
    Page<T> getAll(Pageable pageable) throws RuntimeException;

    /**
     * Get the all entities basis of sort parameter.
     *
     * @param sort
     *            the sort
     * @return the all
     * @throws RuntimeException
     *             the RuntimeException
     */
    List<T> getAll(Sort sort) throws RuntimeException;

    /**
     * Persist the provided entity.
     *
     * @param entity
     *            the entity
     * @return the t
     * @throws RuntimeException
     *             the RuntimeException
     */
    T save(T entity) throws RuntimeException;

    /**
     * Persist all the provided entities.
     *
     * @param entities
     *            the entities
     * @return the list
     * @throws RuntimeException
     *             the RuntimeException
     */
    List<T> save(Iterable<T> entities) throws RuntimeException;

    /**
     * Delete entity by its id.
     *
     * @param entityId
     *            the entity id
     * @throws RuntimeException
     *             the RuntimeException
     */
    void delete(Long entityId) throws RuntimeException;

    /**
     * Delete entity object.
     *
     * @param entity
     *            the entity
     * @throws RuntimeException
     *             the RuntimeException
     */
    void delete(T entity) throws RuntimeException;

    /**
     * Delete all the provided entities objects.
     *
     * @param entities
     *            the entities
     * @throws RuntimeException
     *             the RuntimeException
     */
    void delete(Iterable<T> entities) throws RuntimeException;

    /**
     * Delete all the entities in batch from the table.
     * 
     * @throws RuntimeException
     *             the RuntimeException
     */
    void deleteAll() throws RuntimeException;

    /**
     * Gets the list of all entities.
     *
     * @param pageable
     *            the pageable
     * @param predicate
     *            the predicate
     * @return the all
     * @throws RuntimeException
     *             the RuntimeException
     */
    Page<T> getAll(final Pageable pageable, final Predicate predicate) throws RuntimeException;

    /**
     * Gets the entity by predicate. This method will return the matching record as per the passed predicate.
     *
     * @param predicate
     *            the predicate
     * @return the entity by predicate
     * @throws RuntimeException
     *             the RuntimeException
     */
    T getEntityByPredicate(final Predicate predicate) throws RuntimeException;

    /**
     * Gets the entity by predicate and order specifier. This method will return the matching record as per the passed
     * predicate and order specifier.
     *
     * @param predicate
     *            the predicate
     * @param orderSpecifier
     *            the order specifier
     * @return the all
     * @throws RuntimeException
     *             the RuntimeException
     */
    List<T> getAll(Predicate predicate, OrderSpecifier<?> orderSpecifier) throws RuntimeException;

    /**
     * Delete all the entities with constraint mappings.
     * 
     * @param entities
     *            the entities
     * @throws RuntimeException
     *             the RuntimeException
     */
    void deleteEntities(Iterable<T> entities) throws RuntimeException;


    /**
     * Gets the count of entity by predicate.
     * 
     * @param predicate
     *            the predicate
     * @return the count
     * @throws RuntimeException
     *             the RuntimeException
     */
    Long getCount(Predicate predicate) throws RuntimeException;

    /**
     * This method executes the given query with the given JDBCTempate for the system.
     * 
     * @param jdbcTemplate
     *            the jdbc template
     * @param queryString
     *            the query string
     * @throws RuntimeException
     *             the RuntimeException
     */
    void executeQuery(JdbcTemplate jdbcTemplate, @Nonnull String queryString) throws RuntimeException;
}
