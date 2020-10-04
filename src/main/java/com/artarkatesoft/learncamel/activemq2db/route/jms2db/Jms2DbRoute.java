package com.artarkatesoft.learncamel.activemq2db.route.jms2db;

import com.artarkatesoft.learncamel.activemq2db.route.jdbc.InsertProcessor;
import org.apache.camel.builder.RouteBuilder;

public class Jms2DbRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("activemq:queue:testQueue")
                .log("Received Message is ${body}")
                .process(new InsertProcessor())
                .to("jdbc:myDataSource")
                .to("sql:select * from messages?dataSource=myDataSource")
                .to("log:?showBody=true&level=INFO")
                .to("direct:output");
    }
}
