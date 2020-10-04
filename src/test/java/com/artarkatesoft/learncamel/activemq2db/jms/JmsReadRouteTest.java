package com.artarkatesoft.learncamel.activemq2db.jms;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JmsReadRouteTest extends CamelTestSupport {
    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new JmsReadRoute();
    }

    @Test
    void readMessageFromActiveMQ() {
        //given
        String expectedMessage = "123";

        // when
        String actualMessage = consumer.receiveBody("direct:readQueue", String.class);

        //then
        assertEquals(expectedMessage, actualMessage);
    }
}
