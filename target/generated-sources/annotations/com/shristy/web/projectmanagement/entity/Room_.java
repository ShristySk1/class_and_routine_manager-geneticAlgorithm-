package com.shristy.web.projectmanagement.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Room.class)
public abstract class Room_ {

	public static volatile SingularAttribute<Room, String> roomAssigned;
	public static volatile SingularAttribute<Room, Department> departmentId;
	public static volatile SingularAttribute<Room, Long> roomId;
	public static volatile SingularAttribute<Room, String> roomValue;

}

