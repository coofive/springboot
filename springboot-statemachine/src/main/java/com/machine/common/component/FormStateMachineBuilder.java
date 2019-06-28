package com.machine.common.component;

import com.machine.common.constants.FormEvents;
import com.machine.common.constants.FormStates;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.stereotype.Component;

import java.util.EnumSet;

/**
 * @author wenwu.liu.o
 */
@Slf4j
@Component
public class FormStateMachineBuilder {

    private final static String MACHINE_ID = "formMachine";

    /**
     * 构建状态机
     *
     * @param beanFactory 工厂
     * @return StateMachine
     * @throws Exception ex
     */
    public StateMachine<FormStates, FormEvents> build(BeanFactory beanFactory) throws Exception {
        StateMachineBuilder.Builder<FormStates, FormEvents> builder = StateMachineBuilder.builder();

        log.info("----构建表单状态机----");
        builder.configureConfiguration().withConfiguration().machineId(MACHINE_ID).beanFactory(beanFactory);

        builder.configureStates().withStates().initial(FormStates.BLANK_FORM).states(EnumSet.allOf(FormStates.class));

        builder.configureTransitions()
                .withExternal().source(FormStates.BLANK_FORM).target(FormStates.FULL_FORM).event(FormEvents.WRITE).and()
                .withExternal().source(FormStates.FULL_FORM).target(FormStates.CONFIRM_FORM).event(FormEvents.CONFIRM).and()
                .withExternal().source(FormStates.CONFIRM_FORM).target(FormStates.SUCCESS_FORM).event(FormEvents.SUBMIT);
        return builder.build();
    }
}
