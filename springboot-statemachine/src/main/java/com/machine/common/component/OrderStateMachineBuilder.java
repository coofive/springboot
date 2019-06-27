package com.machine.common.component;

import com.machine.common.constants.OrderEvents;
import com.machine.common.constants.OrderStates;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.stereotype.Component;

import java.util.EnumSet;

/**
 * @author wenwu.liu.o
 */
@Slf4j
@Component
public class OrderStateMachineBuilder {

    private final static String MACHINE_ID = "orderMachine";

    public StateMachine<OrderStates, OrderEvents> build(BeanFactory beanFactory) throws Exception {
        StateMachineBuilder.Builder<OrderStates, OrderEvents> builder = StateMachineBuilder.builder();
        log.info("----构建订单状态机----");
        builder.configureConfiguration().withConfiguration().machineId(MACHINE_ID).beanFactory(beanFactory);

        builder.configureStates().withStates().initial(OrderStates.UNPAID).states(EnumSet.allOf(OrderStates.class));

        builder.configureTransitions()
                .withExternal().source(OrderStates.UNPAID).target(OrderStates.WAITING_FOR_RECEIVE).event(OrderEvents.PAY).action(action()).and()
                .withExternal().source(OrderStates.WAITING_FOR_RECEIVE).target(OrderStates.DONE).event(OrderEvents.RECEIVE);
        return builder.build();
    }

    @Bean
    public Action<OrderStates, OrderEvents> action() {
        return context -> log.info("context:{}", context);
    }
}
