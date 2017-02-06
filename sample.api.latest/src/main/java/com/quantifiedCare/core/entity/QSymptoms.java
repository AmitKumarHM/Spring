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
 * QSymptoms is a Querydsl query type for Symptoms
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSymptoms extends EntityPathBase<Symptoms> {

    private static final long serialVersionUID = -493559442;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSymptoms symptoms = new QSymptoms("symptoms");

    public final QAbstractBaseEntity _super = new QAbstractBaseEntity(this);

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final BooleanPath new$ = _super.new$;

    public final StringPath symptom = createString("symptom");

    public final StringPath typeOfSymptom = createString("typeOfSymptom");

    public final DateTimePath<java.util.Date> updatedDate = createDateTime("updatedDate", java.util.Date.class);

    public final QUsers user;

    public QSymptoms(String variable) {
        this(Symptoms.class, forVariable(variable), INITS);
    }

    @SuppressWarnings("all")
    public QSymptoms(Path<? extends Symptoms> path) {
        this((Class)path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSymptoms(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSymptoms(PathMetadata<?> metadata, PathInits inits) {
        this(Symptoms.class, metadata, inits);
    }

    public QSymptoms(Class<? extends Symptoms> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUsers(forProperty("user"), inits.get("user")) : null;
    }

}

