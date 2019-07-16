package com.machine.common.config;

import com.machine.common.cache.InMemoryStateMachinePersist;
import com.machine.common.constants.OrderEvents;
import com.machine.common.constants.OrderStates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.RepositoryStateMachinePersist;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.statemachine.redis.RedisStateMachineContextRepository;

import javax.annotation.Resource;

/**
 * @author wenwu.liu.o
 */
@Configuration
public class PersistConfig {

    @Resource
    private InMemoryStateMachinePersist inMemoryStateMachinePersist;

    @Resource
    private RedisConnectionFactory redisConnectionFactory;

    /**
     * 注入StateMachinePersister对象
     */
    @Bean(name = "orderMemoryPersister")
    public StateMachinePersister<OrderStates, OrderEvents, String> getPersister() {
        return new DefaultStateMachinePersister<>(redisPersist());
    }

    public StateMachinePersist<OrderStates, OrderEvents, String> redisPersist() {
        RedisStateMachineContextRepository<OrderStates, OrderEvents> repository = new RedisStateMachineContextRepository<>(redisConnectionFactory);
        return new RepositoryStateMachinePersist<>(repository);
    }
}
