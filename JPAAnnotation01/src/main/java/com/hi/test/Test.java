package com.hi.test;

import com.hi.entity.Student;
import com.hi.helper.HelperClass;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

public class Test {
	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;

		boolean flag = false;
		Student student = null;

		try {
			entityManagerFactory = HelperClass.getEntityManagerFactory();
			entityManager = entityManagerFactory.createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();

			student =  new Student();
			student.setStName("Abc");
			
			entityManager.persist(student);
			
			System.out.println();

			flag=true;
			
		} finally {
			if (entityTransaction != null) {
				if (flag) {
					entityTransaction.commit();
				} else {
					entityTransaction.rollback();
				}
			}
			if (entityManager != null) {
				entityManager.close();
			}
			HelperClass.closeEntityManagerFactory();
		}
	}
}
