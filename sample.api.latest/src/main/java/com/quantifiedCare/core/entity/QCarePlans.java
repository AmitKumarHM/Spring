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
 * QCarePlans is a Querydsl query type for CarePlans
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCarePlans extends EntityPathBase<CarePlans> {

    private static final long serialVersionUID = 1650534485;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCarePlans carePlans = new QCarePlans("carePlans");

    public final QAbstractBaseEntity _super = new QAbstractBaseEntity(this);

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    //inherited
    public final BooleanPath new$ = _super.new$;

    public final QUsers patient;

    public final DateTimePath<java.util.Date> updatedDate = createDateTime("updatedDate", java.util.Date.class);

    public final QUsers user;

    public QCarePlans(String variable) {
        this(CarePlans.class, forVariable(variable), INITS);
    }

    @SuppressWarnings("all")
    public QCarePlans(Path<? extends CarePlans> path) {
        this((Class)path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCarePlans(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCarePlans(PathMetadata<?> metadata, PathInits inits) {
        this(CarePlans.class, metadata, inits);
    }

    public QCarePlans(Class<? extends CarePlans> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.patient = inits.isInitialized("patient") ? new QUsers(forProperty("patient"), inits.get("patient")) : null;
        this.user = inits.isInitialized("user") ? new QUsers(forProperty("user"), inits.get("user")) : null;
    }

}

