package com.artarkatesoft.learncamel.activemq2db.launch;


import com.artarkatesoft.learncamel.activemq2db.route.jms2db.Jms2DbRoute;
import org.apache.camel.main.Main;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

public class AppLauncher {

    private Main main;

    public static void main(String[] args) throws Exception {

        AppLauncher launcher = new AppLauncher();

        launcher.boot();


    }

    public void boot() throws Exception {
        // create a Main instance
        main = new Main();

        String url = "jdbc:postgresql://localhost:5432/activemq_dilip_tutorial_camel_java";
        DataSource dataSource = setupDataSource(url);

        main.bind("myDataSource", dataSource);

        // enable hangup support so you can press ctrl + c to terminate the JVM
//        main.enableHangupSupport();
        // add routes
        main.configure().addRoutesBuilder(new Jms2DbRoute());

        // run until you terminate the JVM
        System.out.println("Starting Camel JMS to DB Route. Use ctrl + c to terminate the JVM.\n");
        main.run();
    }

    private DataSource setupDataSource(String url) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUsername("postgres");
        dataSource.setPassword("123");
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(url);
        return dataSource;
    }
}
