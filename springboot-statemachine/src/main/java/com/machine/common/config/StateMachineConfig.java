package com.machine.common.config;

import com.machine.common.constants.OrderEvents;
import com.machine.common.constants.OrderStates;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

/**
 * @author wenwu.liu.o
 */
@Slf4j
@Configuration
@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<OrderStates, OrderEvents> {

    @Override
    public void configure(StateMachineStateConfigurer<OrderStates, OrderEvents> states) throws Exception {
        states.withStates().initial(OrderStates.UNPAID).states(EnumSet.allOf(OrderStates.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStates, OrderEvents> transitions) throws Exception {
        // 支付
        transitions.withExternal().source(OrderStates.UNPAID).target(OrderStates.WAITING_FOR_RECEIVE).event(OrderEvents.PAY)
                // 收货
                .and().withExternal().source(OrderStates.WAITING_FOR_RECEIVE).target(OrderStates.DONE).event(OrderEvents.RECEIVE);
    }

}
