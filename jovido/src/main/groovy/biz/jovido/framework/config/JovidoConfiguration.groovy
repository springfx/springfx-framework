package biz.jovido.framework.config

import biz.jovido.framework.CustomerScenario
import biz.jovido.framework.control.CustomerList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary

/**
 *
 * @author Stephan Grundner
 */
@EnableAutoConfiguration
class JovidoConfiguration {

    @Autowired
    ApplicationContext applicationContext

    @Bean
    @Primary
    CustomerScenario customerScenario() {
        new CustomerScenario()
    }

    @Bean
    CustomerList customerList() {
        new CustomerList()
    }
}
