package com.machine.common.cache;

import com.machine.common.constants.OrderEvents;
import com.machine.common.constants.OrderStates;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wenwu.liu.o
 */
@Component
public class InMemoryStateMachinePersist implements StateMachinePersist<OrderStates, OrderEvents, String> {
    public static Map<String, StateMachineContext<OrderStates, OrderEvents>> orderMap = new HashMap<>();

    @Override
    public void write(StateMachineContext<OrderStates, OrderEvents> stateMachineContext, String contextObj) throws Exception {
        orderMap.put(contextObj, stateMachineContext);
    }

    @Override
    public StateMachineContext<OrderStates, OrderEvents> read(String contextObj) throws Exception {
        return orderMap.get(contextObj);
    }
}
