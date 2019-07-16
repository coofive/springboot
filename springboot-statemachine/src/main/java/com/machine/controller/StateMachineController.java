package com.machine.controller;

import com.machine.common.cache.MachineMap;
import com.machine.common.component.FormStateMachineBuilder;
import com.machine.common.component.OrderStateMachineBuilder;
import com.machine.common.constants.FormEvents;
import com.machine.common.constants.FormStates;
import com.machine.common.constants.OrderEvents;
import com.machine.common.constants.OrderStates;
import com.machine.entity.Form;
import com.machine.entity.Order;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;
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
        Order order = new Order().setOrderId("1").setAddress("上海市闵行区漕河泾").setMobile("17187402175").setType("RECEIVE");
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

    @Resource(name = "orderMemoryPersister")
    private StateMachinePersister<OrderStates, OrderEvents, String> orderMemoryPersister;


    @GetMapping("/test4")
    public void testSendEvent(String machineId, String events, String id) throws Exception {
        if ("form".equals(machineId)) {
            StateMachine sm = MachineMap.formMap.get(id);
            Form form = new Form();
            form.setFormId(id);
            if (sm == null) {
                if ("WRITE".equals(events)) {
                    sm = formStateMachineBuilder.build(beanFactory);
                    sm.start();
                    MachineMap.formMap.put(id, sm);
                } else {
                    System.out.println("该表单流程尚未开始，不能做" + events + "操作！");
                    return;
                }
            }
            Message<FormEvents> message = MessageBuilder.withPayload(FormEvents.valueOf(events)).setHeader("form", form).build();
            sm.sendEvent(message);
        }
        if ("order".equals(machineId)) {
            StateMachine sm = orderStateMachineBuilder.build(beanFactory);

            orderMemoryPersister.restore(sm, id);
            System.out.println("sm.getState().getId() = " + sm.getState().getId());
            Order order = new Order().setOrderId(id);
            if ("PAY".equals(events)) {
                sm.start();
            }
            Message<OrderEvents> message = MessageBuilder.withPayload(OrderEvents.valueOf(events)).setHeader("order", order).build();
            sm.sendEvent(message);

            System.out.println("sm.getState().getId() = " + sm.getState().getId());
            // 持久化
            orderMemoryPersister.persist(sm, order.getOrderId());
        }
    }
}