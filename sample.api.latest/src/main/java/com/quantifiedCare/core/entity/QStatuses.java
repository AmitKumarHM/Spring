package com.quantifiedCare.core.entity;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.BooleanPath;
import com.mysema.query.types.path.EntityPathBase;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.StringPath;


/**
 * QStatuses is a Querydsl query type for Statuses
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QStatuses extends EntityPathBase<Statuses> {

    private static final long serialVersionUID = -975932892;

    public static final QStatuses statuses = new QStatuses("statuses");

    public final QAbstractBaseEntity _super = new QAbstractBaseEntity(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final BooleanPath new$ = _super.new$;

    public final StringPath status = createString("status");

    public QStatuses(String variable) {
        super(Statuses.class, forVariable(variable));
    }

    @SuppressWarnings("all")
    public QStatuses(Path<? extends Statuses> path) {
        super((Class)path.getType(), path.getMetadata());
    }

    public QStatuses(PathMetadata<?> metadata) {
        super(Statuses.class, metadata);
    }

}

