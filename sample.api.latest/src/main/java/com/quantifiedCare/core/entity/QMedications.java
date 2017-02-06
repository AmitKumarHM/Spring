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
 * QMedications is a Querydsl query type for Medications
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMedications extends EntityPathBase<Medications> {

    private static final long serialVersionUID = 1341019904;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMedications medications = new QMedications("medications");

    public final QAbstractBaseEntity _super = new QAbstractBaseEntity(this);

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final StringPath dayOfMedication = createString("dayOfMedication");

    public final NumberPath<Integer> dosage = createNumber("dosage", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.util.Date> lastFilledDate = createDateTime("lastFilledDate", java.util.Date.class);

    public final NumberPath<Integer> lastFilledQuantity = createNumber("lastFilledQuantity", Integer.class);

    public final StringPath medication = createString("medication");

    //inherited
    public final BooleanPath new$ = _super.new$;

    public final DateTimePath<java.util.Date> timeOfMedication = createDateTime("timeOfMedication", java.util.Date.class);

    public final DateTimePath<java.util.Date> updatedDate = createDateTime("updatedDate", java.util.Date.class);

    public final QUsers user;

    public QMedications(String variable) {
        this(Medications.class, forVariable(variable), INITS);
    }

    @SuppressWarnings("all")
    public QMedications(Path<? extends Medications> path) {
        this((Class)path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMedications(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMedications(PathMetadata<?> metadata, PathInits inits) {
        this(Medications.class, metadata, inits);
    }

    public QMedications(Class<? extends Medications> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUsers(forProperty("user"), inits.get("user")) : null;
    }

}

