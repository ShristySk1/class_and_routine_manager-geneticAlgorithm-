package com.shristy.web.projectmanagement.entity;

import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Student.class)
public abstract class Student_ {

	public static volatile SingularAttribute<Student, Long> studentId;
	public static volatile SingularAttribute<Student, String> stdadd;
	public static volatile SingularAttribute<Student, String> studentEmail;
	public static volatile SingularAttribute<Student, BigInteger> stdcon;
	public static volatile SingularAttribute<Student, Integer> stdbatch;
	public static volatile SingularAttribute<Student, String> studentName;
	public static volatile SingularAttribute<Student, Integer> rollno;
	public static volatile SingularAttribute<Student, Courses> course;
	public static volatile SingularAttribute<Student, String> section;
	public static volatile SingularAttribute<Student, AppUser> user;

}

