package hello.springtx.apply

import io.kotest.core.spec.style.ShouldSpec
import mu.KotlinLogging
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.support.TransactionSynchronizationManager

val log = KotlinLogging.logger {}

@SpringBootTest
class TxBasicTest : ShouldSpec(
    {

    }
) {
    internal open class BasicService3 {
        @Transactional
        open fun tx() {
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

}