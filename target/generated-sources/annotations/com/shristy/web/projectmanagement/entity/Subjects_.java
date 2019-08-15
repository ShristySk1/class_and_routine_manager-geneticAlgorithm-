package com.shristy.web.projectmanagement.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Subjects.class)
public abstract class Subjects_ {

	public static volatile SingularAttribute<Subjects, Department> departmentId;
	public static volatile SingularAttribute<Subjects, String> subjectCode;
	public static volatile SingularAttribute<Subjects, Integer> periodPerWeek;
	public static volatile SingularAttribute<Subjects, Courses> courseId;
	public static volatile SingularAttribute<Subjects, Long> subjectId;
	public static volatile SingularAttribute<Subjects, Boolean> isLab;
	public static volatile SingularAttribute<Subjects, String> subjectName;

}

