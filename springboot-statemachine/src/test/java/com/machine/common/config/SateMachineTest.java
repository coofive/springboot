package com.machine.common.config;

import com.machine.common.constants.OrderEvents;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author wenwu.liu.o
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SateMachineTest {

    @Resource
    private StateMachine OrderSingleEventConfig;

    @Test
    public void testSingleMachine() {

        // 创建流程
        OrderSingleEventConfig.start();

        // 触发PAY事件
        OrderSingleEventConfig.sendEvent(OrderEvents.PAY);

        // 触发RECEIVE事件
        OrderSingleEventConfig.sendEvent(OrderEvents.RECEIVE);
        // 获取最终状态
        System.out.println("最终状态：" + OrderSingleEventConfig.getState().getId());
    }
}