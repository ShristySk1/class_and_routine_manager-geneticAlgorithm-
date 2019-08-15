package com.shristy.web.projectmanagement.entity;

import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Teacher.class)
public abstract class Teacher_ {

	public static volatile SingularAttribute<Teacher, String> teacherEmail;
	public static volatile SingularAttribute<Teacher, String> teacherType;
	public static volatile SingularAttribute<Teacher, Long> teacherId;
	public static volatile SingularAttribute<Teacher, BigInteger> teacherContact;
	public static volatile SingularAttribute<Teacher, String> teacherName;
	public static volatile SingularAttribute<Teacher, String> teacherPicture;
	public static volatile SingularAttribute<Teacher, Date> teacherWorkinghr;
	public static volatile SingularAttribute<Teacher, AppUser> user;
	public static volatile ListAttribute<Teacher, TeacherSubject> teacherSubjectList;
	public static volatile SingularAttribute<Teacher, Boolean> status;

}

