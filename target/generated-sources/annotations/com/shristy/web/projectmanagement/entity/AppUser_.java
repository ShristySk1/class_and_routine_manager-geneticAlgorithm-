package com.shristy.web.projectmanagement.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AppUser.class)
public abstract class AppUser_ {

	public static volatile SetAttribute<AppUser, AppRole> roles;
	public static volatile SingularAttribute<AppUser, String> fullName;
	public static volatile SingularAttribute<AppUser, String> userName;
	public static volatile SingularAttribute<AppUser, Long> userId;
	public static volatile SingularAttribute<AppUser, String> email;
	public static volatile SingularAttribute<AppUser, String> encrytedPassword;
	public static volatile SingularAttribute<AppUser, Boolean> enabled;

}

