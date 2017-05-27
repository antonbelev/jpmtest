package com.antonbelev.jpmmessageprocessor.service.impl;

import com.antonbelev.jpmmessageprocessor.datastore.DataStore;
import com.antonbelev.jpmmessageprocessor.model.Event;
import com.antonbelev.jpmmessageprocessor.service.MessageProcessor;
import com.antonbelev.jpmmessageprocessor.exceptions.EmptyMessageQueueException;
import com.antonbelev.jpmmessageprocessor.exceptions.EventHandlerException;
import com.antonbelev.jpmmessageprocessor.factory.EventHandlerFactory;
import com.antonbelev.jpmmessageprocessor.service.EventHandler;
import com.antonbelev.jpmmessageprocessor.service.ReportGenerator;

public class SalesMessageProcessor implements MessageProcessor {
    private final ReportGenerator reportGenerator;

    public SalesMessageProcessor(final ReportGenerator reportGenerator) {
        this.reportGenerator = reportGenerator;
    }

    @Override
    public void startProcessing() {
        System.out.println("Message processor has started!");

        int eventsProcessed = 0;
        while (DataStore.hasNextEvent()) {
            Event event;
            try {
                event = DataStore.nextEvent();
            } catch (EmptyMessageQueueException e) {
                System.err.println(e.getMessage());
                return;
            }

            boolean success = processEvent(event);
            if (!success) {
                continue;
            }
            eventsProcessed++;

            if (eventsProcessed % 10 == 0) {
                String salesReport = reportGenerator.generateSalesReport(DataStore.salesHistory);
                System.out.println(salesReport);
            }
            if (eventsProcessed == 50) {
                String adjustmentsReport = reportGenerator.generateAdjustmentsReport(DataStore.adjustmentsHistory);
                System.out.println(adjustmentsReport);
                break;
            }
        }

        System.out.println("Message processor has finished.");
    }

    private boolean processEvent(Event event) {
        EventHandler eventHandler = EventHandlerFactory.getHandler(event.getMessageType());
        if (eventHandler == null) {
            System.err.println("Error while getting EventHandler impl for type=" + event.getMessageType());
            return false;
        }

        try {
            eventHandler.handleEvent(event);
        } catch (EventHandlerException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }
}
