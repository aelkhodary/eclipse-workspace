package com.scad.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.scad.hibernate.entity.Instructor;
import com.scad.hibernate.entity.InstructorDetail;

public class DeleteInstructorDetailDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

			// start a transaction
			session.beginTransaction();

			// TODO get the instructor detail object

			int theId = 3;

			InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, theId);

			// TODO print the instructor detail

			System.out.println("tempInstructorDetail:" + tempInstructorDetail.toString());

			// TODO print the associated instructor

			System.out.println("the associated Instructor:" + tempInstructorDetail.getInstructor().toString());

			// TODO let's delete the instructor detail

			System.out.println("Deleting tempInstructorDetail:" + tempInstructorDetail);

			// TODO remove deleted object from associations
			/*
			 * Incase you want to delete only instructorDetail row and keep master
			 * instructor row
			 */
			tempInstructorDetail.getInstructor().setInstructorDetail(null);  
			
	
			session.delete(tempInstructorDetail);

			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done!");
		}

		finally {
			// TODO Handling connection leak issue
			session.close();
			factory.close();
		}
	}

}
