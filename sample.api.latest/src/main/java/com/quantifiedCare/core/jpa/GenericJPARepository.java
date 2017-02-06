package com.quantifiedCare.core.jpa;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * Generic Repository for all other JPA business repositories.
 * All other repositories will extend this repository and pass their corresponding entity in its type parameter
 *
 * @param <T>
 *            Type of the business object
 */
public interface GenericJPARepository<T extends Serializable> extends JpaRepository<T, Long>,
        QueryDslPredicateExecutor<T>, Serializable {
// base repository of all repositories
}
