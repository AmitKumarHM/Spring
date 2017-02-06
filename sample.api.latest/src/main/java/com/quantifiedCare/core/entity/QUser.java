package com.quantifiedCare.core.entity;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.EntityPathBase;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.PathInits;
import com.mysema.query.types.path.StringPath;


@Generated("com.mysema.query.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User>{

	
	private static final PathInits INITS = PathInits.DIRECT2;
	private static final long serialVersionUID = -734430368190070464L;
	public static final QUser users = new QUser("user");
	
	public final StringPath userName = createString("userName");
    public final StringPath password = createString("password");
    public final StringPath emailId = createString("emailId");
    public final NumberPath<Long> id = createNumber("id", Long.class);
	
	public QUser(String variable) {
	        this(User.class, forVariable(variable), INITS);
	    }
	
	@SuppressWarnings("all")
    public QUser(Path<? extends User> path) {
        this((Class)path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUser(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUser(PathMetadata<?> metadata, PathInits inits) {
        this(User.class, metadata, inits);
    }
    
    public QUser(Class<? extends User> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
    }
}
