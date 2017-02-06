package com.quantifiedCare.core.entity;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.BooleanPath;
import com.mysema.query.types.path.EntityPathBase;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.PathInits;
import com.mysema.query.types.path.StringPath;


/**
 * QTheme is a Querydsl query type for Theme
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTheme extends EntityPathBase<Theme> {

    private static final long serialVersionUID = 1485343301;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTheme theme = new QTheme("theme");

    public final QAbstractBaseEntity _super = new QAbstractBaseEntity(this);

    public final StringPath addTagline = createString("addTagline");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final BooleanPath new$ = _super.new$;

    public final QOrganizations organizations;

    public final StringPath orgLogo = createString("orgLogo");

    public final StringPath setHeadingTextColor = createString("setHeadingTextColor");

    public final StringPath setParagraphColor = createString("setParagraphColor");

    public final StringPath setSplashColour = createString("setSplashColour");

    public final StringPath setTopHeader = createString("setTopHeader");

    public QTheme(String variable) {
        this(Theme.class, forVariable(variable), INITS);
    }

    @SuppressWarnings("all")
    public QTheme(Path<? extends Theme> path) {
        this((Class)path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTheme(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTheme(PathMetadata<?> metadata, PathInits inits) {
        this(Theme.class, metadata, inits);
    }

    public QTheme(Class<? extends Theme> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.organizations = inits.isInitialized("organizations") ? new QOrganizations(forProperty("organizations"), inits.get("organizations")) : null;
    }

}

