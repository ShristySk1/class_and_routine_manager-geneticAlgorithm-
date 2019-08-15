package com.shristy.web.projectmanagement.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Todolist.class)
public abstract class Todolist_ {

	public static volatile SingularAttribute<Todolist, Integer> listId;
	public static volatile SingularAttribute<Todolist, String> listTitle;
	public static volatile SingularAttribute<Todolist, Date> createdDate;
	public static volatile SingularAttribute<Todolist, String> listNotice;
	public static volatile SingularAttribute<Todolist, String> listSem;

}

