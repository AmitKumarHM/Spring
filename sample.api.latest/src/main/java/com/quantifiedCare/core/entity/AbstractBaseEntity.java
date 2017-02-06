package com.quantifiedCare.core.entity;

import javax.persistence.MappedSuperclass;
import javax.persistence.metamodel.StaticMetamodel;

import org.springframework.data.domain.Persistable;

/**
 * The Base Model class of all the entity classes: AbstractBaseEntity.
 */
@MappedSuperclass
@StaticMetamodel(AbstractBaseEntity.class)
public abstract class AbstractBaseEntity implements Persistable<Long> {
	/** The Constant serialVersionUID. */
    private static final long serialVersionUID = -7619363516004458063L;

    public boolean isNew() {
        return null == getId();
    }
   
}
