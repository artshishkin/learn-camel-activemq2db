package com.artarkatesoft.learncamel.activemq2db.route.jdbc;

import org.apache.camel.CamelContext;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.SimpleRegistry;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;

class DBPostgresRouteTest extends CamelTestSupport {

    @Override
    protected CamelContext createCamelContext() throws Exception {
        String url = "jdbc:postgresql://localhost:5432/activemq_dilip_tutorial_camel_java";
        DataSource dataSource = setupDataSource(url);

        SimpleRegistry registry = new SimpleRegistry();
        registry.bind("myDataSource", dataSource);
        DefaultCamelContext context = new DefaultCamelContext(registry);
        return context;
    }

    private DataSource setupDataSource(String url) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUsername("postgres");
        dataSource.setPassword("123");
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(url);
        return dataSource;
    }

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new DBPostgresRoute();
    }

    @Test
    void insertData() {
        //given
        String input = "second db input";

        //when
        Object requestBody = template.requestBody("direct:dbInput", input);

        //then
        System.out.println("requestBody=" + requestBody);
    }
}
