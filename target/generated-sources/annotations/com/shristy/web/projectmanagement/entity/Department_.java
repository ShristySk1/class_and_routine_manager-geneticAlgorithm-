package com.shristy.web.projectmanagement.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Department.class)
public abstract class Department_ {

	public static volatile SingularAttribute<Department, String> departmentName;
	public static volatile ListAttribute<Department, Courses> coursesList;
	public static volatile ListAttribute<Department, Subjects> subjectsList;
	public static volatile SingularAttribute<Department, Long> departmentId;
	public static volatile SingularAttribute<Department, String> departmentType;
	public static volatile ListAttribute<Department, Room> roomList;

}

