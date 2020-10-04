package com.artarkatesoft.learncamel.activemq2db.exception;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionProcessor implements org.apache.camel.Processor {

    Logger log = LoggerFactory.getLogger(ExceptionProcessor.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        Exception caughtException = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
        log.error("Actual exception Message: {}", caughtException.getMessage());
        log.error("Actual exception Class: {}", caughtException.getClass());

        String failedEndpoint = exchange.getProperty(Exchange.FAILURE_ENDPOINT, String.class);
        log.error("Failed endpoint: {}", failedEndpoint);

        exchange.getIn().setBody("Exception happened in the route");
    }
}
