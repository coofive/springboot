package com.machine.common.cache;

import com.machine.common.constants.FormEvents;
import com.machine.common.constants.FormStates;
import com.machine.common.constants.OrderEvents;
import com.machine.common.constants.OrderStates;
import org.springframework.statemachine.StateMachine;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wenwu.liu.o
 */
public class MachineMap {
    public static Map<String, StateMachine<OrderStates, OrderEvents>> orderMap = new HashMap<>();
    public static Map<String, StateMachine<FormStates, FormEvents>> formMap = new HashMap<>();
}
