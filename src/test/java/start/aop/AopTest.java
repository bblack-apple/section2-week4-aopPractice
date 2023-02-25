package start.aop;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import start.aop.order.OrderRepository;
import start.aop.order.OrderService;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@Slf4j
@SpringBootTest
public class AopTest {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Test
    void aopInfo() {
        // AopUtils.isAopProxy() 를 사용하면 AOP 프록시가 적용 되었는지 확인할 수 있다.
        log.info("isAopProxy, orderService={}", AopUtils.isAopProxy(orderService));
        log.info("isAopProxy, orderRepository={}", AopUtils.isAopProxy(orderRepository));
    }

    @Test
    void success() {
        orderService.orderItem("itemAx`");
    }

    @Test
    void exception() {
        assertThatThrownBy(() -> orderService.orderItem("ex")).isInstanceOf(IllegalStateException.class);
    }
}
