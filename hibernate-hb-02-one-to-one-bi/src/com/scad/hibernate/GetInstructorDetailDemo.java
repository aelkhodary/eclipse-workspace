package com.scad.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.scad.hibernate.entity.Instructor;
import com.scad.hibernate.entity.InstructorDetail;

public class GetInstructorDetailDemo {

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
			int theId = 2458;
			InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, theId);
			// TODO print the instructor detail
			System.out.println("tempInstructorDetail:" + tempInstructorDetail.toString());

			// TODO print the associated instructor
			System.out.println("the associated Instructor:" + tempInstructorDetail.getInstructor().toString());
			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done!");
		}

		finally {
			//TODO Handling connection leak issue
			session.close();
			factory.close();
		}
	}

}


