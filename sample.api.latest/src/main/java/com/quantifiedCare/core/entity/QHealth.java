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
 * QHealth is a Querydsl query type for Health
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QHealth extends EntityPathBase<Health> {

    private static final long serialVersionUID = -1545437856;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHealth health = new QHealth("health");

    public final QAbstractBaseEntity _super = new QAbstractBaseEntity(this);

    public final StringPath bmi = createString("bmi");

    public final StringPath bp = createString("bp");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath medAdherence = createString("medAdherence");

    //inherited
    public final BooleanPath new$ = _super.new$;

    public final StringPath physicalActivity = createString("physicalActivity");

    public final DateTimePath<java.util.Date> updatedDate = createDateTime("updatedDate", java.util.Date.class);

    public final QUsers user;

    public final StringPath weight = createString("weight");

    public QHealth(String variable) {
        this(Health.class, forVariable(variable), INITS);
    }

    @SuppressWarnings("all")
    public QHealth(Path<? extends Health> path) {
        this((Class)path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QHealth(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QHealth(PathMetadata<?> metadata, PathInits inits) {
        this(Health.class, metadata, inits);
    }

    public QHealth(Class<? extends Health> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUsers(forProperty("user"), inits.get("user")) : null;
    }

}

