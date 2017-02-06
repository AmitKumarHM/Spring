package com.bluepiit.etl.utils;

import java.io.InputStreamReader;
import java.net.URL;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBUtils {

    private static SqlSessionFactory sessionFactory = buildSessionFactory();
    private static Logger LOG = LoggerFactory.getLogger(DBUtils.class);

    private static SqlSessionFactory buildSessionFactory() {
        try {
            URL conf = DBUtils.class.getClassLoader().getResource("iBatis.xml");
            InputStreamReader inputStream = new InputStreamReader(conf.openStream());
            return new SqlSessionFactoryBuilder().build(inputStream);
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            LOG.error("Initial SessionFactory creation failed." + ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SqlSessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
