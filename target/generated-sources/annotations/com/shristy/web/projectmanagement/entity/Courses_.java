package com.shristy.web.projectmanagement.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Courses.class)
public abstract class Courses_ {

	public static volatile ListAttribute<Courses, Subjects> subjectsList;
	public static volatile SingularAttribute<Courses, String> coursesName;
	public static volatile SingularAttribute<Courses, Department> departmentId;
	public static volatile SingularAttribute<Courses, Long> coursesId;

}

