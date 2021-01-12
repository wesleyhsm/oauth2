package br.com.car.rent.system.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Car.class)
public abstract class Car_ {

	public static volatile SingularAttribute<Car, Date> year;
	public static volatile SingularAttribute<Car, BigDecimal> price;
	public static volatile SingularAttribute<Car, String> model;
	public static volatile SingularAttribute<Car, String> brand;
	public static volatile SingularAttribute<Car, String> board;
	public static volatile SingularAttribute<Car, Long> carId;

}

