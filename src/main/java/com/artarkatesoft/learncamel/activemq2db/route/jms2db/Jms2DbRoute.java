package com.artarkatesoft.learncamel.activemq2db.route.jms2db;

import com.artarkatesoft.learncamel.activemq2db.exception.ExceptionProcessor;
import com.artarkatesoft.learncamel.activemq2db.route.jdbc.InsertProcessor;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.postgresql.util.PSQLException;

public class Jms2DbRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        onException(PSQLException.class)
                .handled(true)
                .log(LoggingLevel.ERROR, "Exception while inserting messages.")
                .process(new ExceptionProcessor());

        from("activemq:queue:testQueue")
                .log("Received Message is ${body}")
                .process(new InsertProcessor())
                .to("jdbc:myDataSource")
                .to("sql:select * from messages?dataSource=myDataSource")
                .to("log:?showBody=true&level=INFO")
                .to("direct:output");
    }
}
