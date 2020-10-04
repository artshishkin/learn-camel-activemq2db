package com.artarkatesoft.learncamel.activemq2db.route.jdbc;

import org.apache.camel.builder.RouteBuilder;

public class DBPostgresRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:dbInput")
                .log("Received message is ${body}")
                .process(new InsertProcessor())
                .to("jdbc:myDataSource")
                .to("sql:select * from messages?dataSource=myDataSource")
                .to("log:?showBody=true&level=INFO");
    }
}
