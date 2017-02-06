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
 * QAccessTokens is a Querydsl query type for AccessTokens
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QAccessTokens extends EntityPathBase<AccessTokens> {

    private static final long serialVersionUID = -497109982;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAccessTokens accessTokens = new QAccessTokens("accessTokens");

    public final QAbstractBaseEntity _super = new QAbstractBaseEntity(this);

    public final StringPath accessToken = createString("accessToken");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final BooleanPath new$ = _super.new$;

    public final QUsers user;

    public QAccessTokens(String variable) {
        this(AccessTokens.class, forVariable(variable), INITS);
    }

    @SuppressWarnings("all")
    public QAccessTokens(Path<? extends AccessTokens> path) {
        this((Class)path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAccessTokens(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAccessTokens(PathMetadata<?> metadata, PathInits inits) {
        this(AccessTokens.class, metadata, inits);
    }

    public QAccessTokens(Class<? extends AccessTokens> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUsers(forProperty("user"), inits.get("user")) : null;
    }

}

