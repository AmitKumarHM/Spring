package com.quantifiedCare.core.entity;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.BooleanPath;
import com.mysema.query.types.path.DatePath;
import com.mysema.query.types.path.DateTimePath;
import com.mysema.query.types.path.EntityPathBase;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.PathInits;
import com.mysema.query.types.path.SetPath;
import com.mysema.query.types.path.StringPath;


/**
 * QUsers is a Querydsl query type for Users
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QUsers extends EntityPathBase<Users> {

    private static final long serialVersionUID = 1486594692;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUsers users = new QUsers("users");

    public final QAbstractBaseEntity _super = new QAbstractBaseEntity(this);

    public final SetPath<AccessTokens, QAccessTokens> accessTokens = this.<AccessTokens, QAccessTokens>createSet("accessTokens", AccessTokens.class, QAccessTokens.class, PathInits.DIRECT2);

    public final StringPath address = createString("address");

    public final NumberPath<Short> age = createNumber("age", Short.class);

    public final StringPath bloodGroup = createString("bloodGroup");

    public final NumberPath<Long> careGiverId = createNumber("careGiverId", Long.class);

    public final StringPath compilance = createString("compilance");

    public final StringPath country = createString("country");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final DatePath<java.util.Date> dateOfBirth = createDate("dateOfBirth", java.util.Date.class);

    public final StringPath emailId = createString("emailId");

    public final StringPath firstName = createString("firstName");

    public final StringPath forgetToken = createString("forgetToken");

    public final StringPath gender = createString("gender");

    public final StringPath height = createString("height");

    public final StringPath homeNumber = createString("homeNumber");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.util.Date> lastContactDate = createDateTime("lastContactDate", java.util.Date.class);

    public final StringPath lastName = createString("lastName");

    public final StringPath medicalCondition = createString("medicalCondition");

    public final SetPath<Medications, QMedications> medications = this.<Medications, QMedications>createSet("medications", Medications.class, QMedications.class, PathInits.DIRECT2);

    public final StringPath memberId = createString("memberId");

    public final SetPath<Messages, QMessages> messages = this.<Messages, QMessages>createSet("messages", Messages.class, QMessages.class, PathInits.DIRECT2);

    public final StringPath middleName = createString("middleName");

    public final StringPath mobileNumber = createString("mobileNumber");

    //inherited
    public final BooleanPath new$ = _super.new$;

    public final StringPath officeNumber = createString("officeNumber");

    public final QOrganizations organization;

    public final StringPath password = createString("password");

    public final NumberPath<Integer> postalCode = createNumber("postalCode", Integer.class);

    public final QRoles role;

    public final QStates state;

    public final QStatuses status;

    public final SetPath<Symptoms, QSymptoms> symptoms = this.<Symptoms, QSymptoms>createSet("symptoms", Symptoms.class, QSymptoms.class, PathInits.DIRECT2);

    public final SetPath<Messages, QMessages> toMessages = this.<Messages, QMessages>createSet("toMessages", Messages.class, QMessages.class, PathInits.DIRECT2);

    public final DateTimePath<java.util.Date> updatedDate = createDateTime("updatedDate", java.util.Date.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public final StringPath userName = createString("userName");

    public final StringPath weight = createString("weight");

    public QUsers(String variable) {
        this(Users.class, forVariable(variable), INITS);
    }

    @SuppressWarnings("all")
    public QUsers(Path<? extends Users> path) {
        this((Class)path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUsers(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUsers(PathMetadata<?> metadata, PathInits inits) {
        this(Users.class, metadata, inits);
    }

    public QUsers(Class<? extends Users> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.organization = inits.isInitialized("organization") ? new QOrganizations(forProperty("organization"), inits.get("organization")) : null;
        this.role = inits.isInitialized("role") ? new QRoles(forProperty("role")) : null;
        this.state = inits.isInitialized("state") ? new QStates(forProperty("state")) : null;
        this.status = inits.isInitialized("status") ? new QStatuses(forProperty("status")) : null;
    }

}

