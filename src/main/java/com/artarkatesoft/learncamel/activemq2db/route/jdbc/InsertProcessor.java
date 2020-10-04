package com.artarkatesoft.learncamel.activemq2db.route.jdbc;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InsertProcessor implements Processor {

    Logger log = LoggerFactory.getLogger(InsertProcessor.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        String input = exchange.getIn().getBody(String.class);
        log.info("Input to be persisted: {}", input);
        //language=SQL
        String insertQuery = "INSERT INTO messages VALUES ('1','" + input + "')";
//        String insertQuery = "INSERT INTO messages1 VALUES ('1','" + input + "')";
        log.info("Input Query is: {}", insertQuery);
        exchange.getIn().setBody(insertQuery);
    }
}
