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
 * QStates is a Querydsl query type for States
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QStates extends EntityPathBase<States> {

    private static final long serialVersionUID = -1216657146;

    public static final QStates states = new QStates("states");

    public final QAbstractBaseEntity _super = new QAbstractBaseEntity(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final BooleanPath new$ = _super.new$;

    public final StringPath state = createString("state");

    public QStates(String variable) {
        super(States.class, forVariable(variable));
    }

    @SuppressWarnings("all")
    public QStates(Path<? extends States> path) {
        super((Class)path.getType(), path.getMetadata());
    }

    public QStates(PathMetadata<?> metadata) {
        super(States.class, metadata);
    }

}

