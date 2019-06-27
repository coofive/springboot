package com.machine.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

/**
 * @author wenwu.liu.o
 */
@Slf4j
@WithStateMachine(id = "orderMachine")
public class OrderSingleEventConfig {

    /**
     * 当前状态UNPAID
     */
    @OnTransition(target = "UNPAID")
    public void create() {
        log.info("----订单创建,待支付----");
    }

    /**
     * UNPAID->WAITING_FOR_RECEIVE 执行的动作
     */
    @OnTransition(source = "UNPAID", target = "WAITING_FOR_RECEIVE")
    public void pay() {
        log.info("----用户完成支付，待收货----");
    }

    /**
     * WAITING_FOR_RECEIVE->DONE 执行的动作
     */
    @OnTransition(source = "WAITING_FOR_RECEIVE", target = "DONE")
    public void receive() {
        log.info("----用户已收货，订单完成----");
    }
}
