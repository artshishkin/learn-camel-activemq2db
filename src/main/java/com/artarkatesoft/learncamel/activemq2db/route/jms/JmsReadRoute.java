package com.artarkatesoft.learncamel.activemq2db.route.jms;

import org.apache.camel.builder.RouteBuilder;

public class JmsReadRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("activemq:queue:testQueue")
                .log("Received Message is ${body}")
                .to("direct:readQueue");
    }
}
