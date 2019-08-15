package com.shristy.web.projectmanagement.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Messages.class)
public abstract class Messages_ {

	public static volatile SingularAttribute<Messages, AppUser> touser;
	public static volatile SingularAttribute<Messages, Long> messageId;
	public static volatile SingularAttribute<Messages, AppUser> fromuser;
	public static volatile SingularAttribute<Messages, String> msgTxt;
	public static volatile SingularAttribute<Messages, AppUser> user;

}

