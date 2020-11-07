package com.trans.investitii.backEnd.dataBase.configuation;

import com.trans.investitii.backEnd.models.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateConfiguration {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory () {
        if (sessionFactory==null) {
            try {
                // creez o configuratie custom pt hibernate
                Configuration configuration = new Configuration();//din org.hibernate.cfg

                // contine setari pentru hibernate (date de conectare),
                // este similar cu un Map - (nume proprietate, valoare prop.)
                Properties properties = new Properties();//java.util

                // adaug setari (conectivitate + comportament hibernate)
                properties.put( Environment.DRIVER, "com.mysql.jdbc.Driver");
                properties.put(Environment.URL, "jdbc:mysql://localhost:3306/iicurs");
                properties.put(Environment.USER, "root");
                properties.put(Environment.PASS, "1234");
                properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                properties.put(Environment.SHOW_SQL, "false");
                properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                properties.put(Environment.HBM2DDL_AUTO, "update");

                // adaug setari in configuratie
                configuration.addProperties(properties);

                // adaugam la configuratie modelele
                configuration.addAnnotatedClass( Invoice.class); //se face clasa in models

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry );
                 final String CREATE_DATABASE = "CREATE DATABASE IF NOT EXISTS cursuri";
                 final String USE_DATABASE = "USE cursuri";

            } catch (Exception e) {
                e.printStackTrace();
            }


        }

        return sessionFactory;
    }
}
