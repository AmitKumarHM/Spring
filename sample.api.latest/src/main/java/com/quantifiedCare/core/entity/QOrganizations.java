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
import com.mysema.query.types.path.SetPath;
import com.mysema.query.types.path.StringPath;


/**
 * QOrganizations is a Querydsl query type for Organizations
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QOrganizations extends EntityPathBase<Organizations> {

    private static final long serialVersionUID = 958370332;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrganizations organizations = new QOrganizations("organizations");

    public final QAbstractBaseEntity _super = new QAbstractBaseEntity(this);

    public final StringPath address = createString("address");

    public final StringPath city = createString("city");

    public final StringPath contactNumber = createString("contactNumber");

    public final StringPath country = createString("country");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    //inherited
    public final BooleanPath new$ = _super.new$;

    public final NumberPath<Integer> postalCode = createNumber("postalCode", Integer.class);

    public final QStates states;

    public final QStatuses status;

    public final QTheme themes;

    public final DateTimePath<java.util.Date> updatedDate = createDateTime("updatedDate", java.util.Date.class);

    public final SetPath<Users, QUsers> users = this.<Users, QUsers>createSet("users", Users.class, QUsers.class, PathInits.DIRECT2);

    public QOrganizations(String variable) {
        this(Organizations.class, forVariable(variable), INITS);
    }

    @SuppressWarnings("all")
    public QOrganizations(Path<? extends Organizations> path) {
        this((Class)path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrganizations(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrganizations(PathMetadata<?> metadata, PathInits inits) {
        this(Organizations.class, metadata, inits);
    }

    public QOrganizations(Class<? extends Organizations> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.states = inits.isInitialized("states") ? new QStates(forProperty("states")) : null;
        this.status = inits.isInitialized("status") ? new QStatuses(forProperty("status")) : null;
        this.themes = inits.isInitialized("themes") ? new QTheme(forProperty("themes"), inits.get("themes")) : null;
    }

}

