package com.example.demo;

import com.example.demo.Entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class TestHibernate {
    public static void main(String[] args) {

        /* ++++ this code loses configuration information ++++

        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        */

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        Transaction t = session.beginTransaction();


        /* ++++ code for creating and saving object ++++

        <---  the constructor is without "id" parameter/argument. @GeneratedValue(strategy = GenerationType.IDENTITY) --->
        Student someNewStudent = new Student("Muster", "Mustermann", "mmuster@outlook.com");
        session.save(someNewStudent);
        t.commit();
        session.close();
        factory.close();
        */


        /* ++++ code for creating and saving object ++++

        Student myStudent = session.get(Student.class, (long) 1);
        System.out.println("\n\n\n");
        System.out.println(myStudent);
        System.out.println("\n\n\n");
        t.commit();
        session.close();
        factory.close();
        */
    }
}
