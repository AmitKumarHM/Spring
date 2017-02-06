package com.quantifiedCare.core.entity;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.BooleanPath;
import com.mysema.query.types.path.DateTimePath;
import com.mysema.query.types.path.EntityPathBase;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.PathInits;
import com.mysema.query.types.path.StringPath;


/**
 * QGoals is a Querydsl query type for Goals
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QGoals extends EntityPathBase<Goals> {

    private static final long serialVersionUID = 1473542204;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGoals goals = new QGoals("goals");

    public final QAbstractBaseEntity _super = new QAbstractBaseEntity(this);

    public final QCarePlans carePlans;

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    //inherited
    public final BooleanPath new$ = _super.new$;

    public final DateTimePath<java.util.Date> updatedDate = createDateTime("updatedDate", java.util.Date.class);

    public QGoals(String variable) {
        this(Goals.class, forVariable(variable), INITS);
    }

    @SuppressWarnings("all")
    public QGoals(Path<? extends Goals> path) {
        this((Class)path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QGoals(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QGoals(PathMetadata<?> metadata, PathInits inits) {
        this(Goals.class, metadata, inits);
    }

    public QGoals(Class<? extends Goals> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.carePlans = inits.isInitialized("carePlans") ? new QCarePlans(forProperty("carePlans"), inits.get("carePlans")) : null;
    }

}

