package com.antonbelev.jpmmessageprocessor.factory;

import com.antonbelev.jpmmessageprocessor.model.enums.MessageType;
import com.antonbelev.jpmmessageprocessor.service.EventHandler;
import com.antonbelev.jpmmessageprocessor.service.impl.AdjustmentEventHandler;
import com.antonbelev.jpmmessageprocessor.service.impl.MultiSaleEventHandler;
import com.antonbelev.jpmmessageprocessor.service.impl.SingleSaleEventHandler;

import java.util.HashMap;
import java.util.Map;

public class EventHandlerFactory {
    private static Map<MessageType, EventHandler> instanceStore;

    static {
        initSingletonStore();
    }

    public static EventHandler getHandler(MessageType type) {
        final EventHandler handler = instanceStore.get(type);

        if (handler == null) {
            System.err.println("Unrecognized event type " + type + ". Ignoring the event");
        }

        return handler;
    }

    // To prevent creation of a number of unused objects
    private static void initSingletonStore() {
        instanceStore = new HashMap<>();
        instanceStore.put(MessageType.SINGLE_SALE, new SingleSaleEventHandler());
        instanceStore.put(MessageType.MULTI_SALE, new MultiSaleEventHandler());
        instanceStore.put(MessageType.ADJUSTMENT, new AdjustmentEventHandler());
    }
}
