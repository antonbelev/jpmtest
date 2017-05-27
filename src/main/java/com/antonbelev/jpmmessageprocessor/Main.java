package com.antonbelev.jpmmessageprocessor;

import com.antonbelev.jpmmessageprocessor.datastore.DataStore;
import com.antonbelev.jpmmessageprocessor.service.MessageProcessor;
import com.antonbelev.jpmmessageprocessor.service.ReportGenerator;
import com.antonbelev.jpmmessageprocessor.service.impl.ReportGeneratorImpl;
import com.antonbelev.jpmmessageprocessor.service.impl.SalesMessageProcessor;

/**
 * This application will generate a number of random Sales events, including single sale, multi sale and adjustment.
 * Then it will process these sales from an event message queue and print reports as required.
 */
public class Main {

    public static void main(String[] args) {
        ReportGenerator reportGenerator = new ReportGeneratorImpl();
        MessageProcessor messageProcessor = new SalesMessageProcessor(reportGenerator);

        ThirdPartyMessageSimulator simulator = new ThirdPartyMessageSimulator();
        DataStore.messageQueue.addAll(simulator.generateEvents());
        messageProcessor.startProcessing();
    }
}
