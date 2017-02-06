package com.quantifiedCare.core.entity;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.BooleanPath;
import com.mysema.query.types.path.EntityPathBase;


/**
 * QAbstractBaseEntity is a Querydsl query type for AbstractBaseEntity
 */
@Generated("com.mysema.query.codegen.SupertypeSerializer")
public class QAbstractBaseEntity extends EntityPathBase<AbstractBaseEntity> {

    private static final long serialVersionUID = -80522022;

    public static final QAbstractBaseEntity abstractBaseEntity = new QAbstractBaseEntity("abstractBaseEntity");

    public final BooleanPath new$ = createBoolean("new");

    public QAbstractBaseEntity(String variable) {
        super(AbstractBaseEntity.class, forVariable(variable));
    }

    @SuppressWarnings("all")
    public QAbstractBaseEntity(Path<? extends AbstractBaseEntity> path) {
        super((Class)path.getType(), path.getMetadata());
    }

    public QAbstractBaseEntity(PathMetadata<?> metadata) {
        super(AbstractBaseEntity.class, metadata);
    }

}

