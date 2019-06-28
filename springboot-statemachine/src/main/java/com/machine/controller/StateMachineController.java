package com.machine.controller;

import com.machine.common.component.FormStateMachineBuilder;
import com.machine.common.component.OrderStateMachineBuilder;
import com.machine.common.constants.FormEvents;
import com.machine.common.constants.FormStates;
import com.machine.common.constants.OrderEvents;
import com.machine.common.constants.OrderStates;
import com.machine.entity.Order;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
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
    private StateMachine orderMachine;

    @RequestMapping("/test")
    public void testSingleOrderState() throws Exception {

        // 创建流程
        orderMachine.start();

        // 触发PAY事件
        orderMachine.sendEvent(OrderEvents.PAY);

        // 触发RECEIVE事件
        orderMachine.sendEvent(OrderEvents.RECEIVE);

        // 获取最终状态
        System.out.println("最终状态：" + orderMachine.getState().getId());
    }

    @Resource
    private OrderStateMachineBuilder orderStateMachineBuilder;
    @Resource
    private BeanFactory beanFactory;

    @GetMapping("/test2")
    public void testOrderStateBuilder() throws Exception {
        StateMachine<OrderStates, OrderEvents> stateMachine = orderStateMachineBuilder.build(beanFactory);
        System.out.println("stateMachine.getId() = " + stateMachine.getId());

        // 创建流程
        stateMachine.start();

        // 触发PAY事件
        stateMachine.sendEvent(OrderEvents.PAY);

        // 触发RECEIVE事件
        Order order = new Order().setOrderId(1).setAddress("上海市闵行区漕河泾").setMobile("17187402175").setType("RECEIVE");
        Message<OrderEvents> message = MessageBuilder.withPayload(OrderEvents.RECEIVE).setHeader("order", order).build();
        stateMachine.sendEvent(message);

        // 获取最终状态
        System.out.println("最终状态：" + stateMachine.getState().getId());
    }

    @Resource
    private FormStateMachineBuilder formStateMachineBuilder;

    @GetMapping("/test3")
    public void testFormStateBuilder() throws Exception {
        StateMachine<FormStates, FormEvents> formMachine = formStateMachineBuilder.build(beanFactory);

        // 创建流程
        formMachine.start();

        // 填写表单
        formMachine.sendEvent(FormEvents.WRITE);

        // 校验表单
        formMachine.sendEvent(FormEvents.CONFIRM);

        // 提交表单
        formMachine.sendEvent(FormEvents.SUBMIT);

        // 获取最终状态
        System.out.println("最终状态：" + formMachine.getState().getId());
    }
}