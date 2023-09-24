package hello.springtx.apply

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import mu.KotlinLogging
import org.springframework.aop.support.AopUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.support.TransactionSynchronizationManager

val log = KotlinLogging.logger {}

@SpringBootTest
class TxBasicTest(
    private val basicService: BasicService
): ShouldSpec(
    {

        should("Proxy Check") {
            log.info { "aop class=${basicService.javaClass}" }
            AopUtils.isAopProxy(basicService) shouldBe true
        }

    }
)

@TestConfiguration
class TxApplyBasicConfig {
    @Bean
    fun basicService() = BasicService()
}

@Service
class BasicService {
    @Transactional
    fun tx() {
        log.info("call tx")
        val txActive: Boolean = TransactionSynchronizationManager.isActualTransactionActive()
        log.info("tx active={}", txActive) // true
    }

    fun nonTx() {
        log.info("call nonTx")
        val txActive: Boolean = TransactionSynchronizationManager.isActualTransactionActive()
        log.info("tx active={}", txActive) // false
    }
}
