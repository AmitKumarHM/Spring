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
 * QRoles is a Querydsl query type for Roles
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRoles extends EntityPathBase<Roles> {

    private static final long serialVersionUID = 1483711289;

    public static final QRoles roles = new QRoles("roles");

    public final QAbstractBaseEntity _super = new QAbstractBaseEntity(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final BooleanPath new$ = _super.new$;

    public final StringPath role = createString("role");

    public QRoles(String variable) {
        super(Roles.class, forVariable(variable));
    }

    @SuppressWarnings("all")
    public QRoles(Path<? extends Roles> path) {
        super((Class)path.getType(), path.getMetadata());
    }

    public QRoles(PathMetadata<?> metadata) {
        super(Roles.class, metadata);
    }

}

