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
 * QMessages is a Querydsl query type for Messages
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMessages extends EntityPathBase<Messages> {

    private static final long serialVersionUID = 1538247504;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMessages messages = new QMessages("messages");

    public final QAbstractBaseEntity _super = new QAbstractBaseEntity(this);

    public final StringPath body = createString("body");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final QUsers fromUser;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final BooleanPath new$ = _super.new$;

    public final StringPath subject = createString("subject");

    public final QUsers toUser;

    public final DateTimePath<java.util.Date> updatedDate = createDateTime("updatedDate", java.util.Date.class);

    public QMessages(String variable) {
        this(Messages.class, forVariable(variable), INITS);
    }

    @SuppressWarnings("all")
    public QMessages(Path<? extends Messages> path) {
        this((Class)path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMessages(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMessages(PathMetadata<?> metadata, PathInits inits) {
        this(Messages.class, metadata, inits);
    }

    public QMessages(Class<? extends Messages> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.fromUser = inits.isInitialized("fromUser") ? new QUsers(forProperty("fromUser"), inits.get("fromUser")) : null;
        this.toUser = inits.isInitialized("toUser") ? new QUsers(forProperty("toUser"), inits.get("toUser")) : null;
    }

}

