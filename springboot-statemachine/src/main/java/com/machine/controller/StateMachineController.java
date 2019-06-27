package com.machine.controller;

import com.machine.common.component.OrderStateMachineBuilder;
import com.machine.common.constants.OrderEvents;
import com.machine.common.constants.OrderStates;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.statemachine.StateMachine;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wenwu.liu.o
 */
@RestController
public class StateMachineController {

    @Resource
    private StateMachine OrderSingleEventConfig;

    @RequestMapping("/test")
    public void testSingleOrderState() throws Exception {

        // 创建流程
        OrderSingleEventConfig.start();

        // 触发PAY事件
        OrderSingleEventConfig.sendEvent(OrderEvents.PAY);

        // 触发RECEIVE事件
        OrderSingleEventConfig.sendEvent(OrderEvents.RECEIVE);

        // 获取最终状态
        System.out.println("最终状态：" + OrderSingleEventConfig.getState().getId());
    }

    @Resource
    private OrderStateMachineBuilder builder;
    @Resource
    private BeanFactory beanFactory;

    @GetMapping("/test2")
    public void testOrderStateBuilder() throws Exception {
        StateMachine<OrderStates, OrderEvents> stateMachine = builder.build(beanFactory);

        // 创建流程
        stateMachine.start();

        // 触发PAY事件
        stateMachine.sendEvent(OrderEvents.PAY);

        // 触发RECEIVE事件
        stateMachine.sendEvent(OrderEvents.RECEIVE);

        // 获取最终状态
        System.out.println("最终状态：" + stateMachine.getState().getId());
    }
}