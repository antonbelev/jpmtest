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

    public static EventHandler getHandler(MessageType type) {
        if (instanceStore == null) {
            initSingletonStore();
        }

        if (type == null) {
            return null;
        }

        if (type == MessageType.SINGLE_SALE) {
            return instanceStore.get(MessageType.SINGLE_SALE);
        } else if (type == MessageType.MULTI_SALE) {
            return instanceStore.get(MessageType.MULTI_SALE);
        } else if (type == MessageType.ADJUSTMENT) {
            return instanceStore.get(MessageType.ADJUSTMENT);
        } else {
            System.err.println("Unrecognized event type " + type + ". Ignoring the event");
            return null;
        }
    }

    // To prevent creation of a number of unused classes
    private static void initSingletonStore() {
        instanceStore = new HashMap<>();
        instanceStore.put(MessageType.SINGLE_SALE, new SingleSaleEventHandler());
        instanceStore.put(MessageType.MULTI_SALE, new MultiSaleEventHandler());
        instanceStore.put(MessageType.ADJUSTMENT, new AdjustmentEventHandler());
    }
}
