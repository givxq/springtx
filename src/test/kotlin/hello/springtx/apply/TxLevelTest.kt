package hello.springtx.apply

import io.kotest.core.spec.style.ShouldSpec
import mu.KotlinLogging
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.support.TransactionSynchronizationManager

@SpringBootTest
class TxLevelTest(
    private val levelService: LevelService
): ShouldSpec({
    should("tx level test") {
        levelService.write()
        levelService.read()
    }
})


@TestConfiguration
class TxLevelConfig {
    @Bean
    fun levelService() = LevelService()
}

@Service
@Transactional(readOnly = true)
class LevelService {
    @Transactional(readOnly = false)
    fun write() {
        log.info { "call write" }
        printTxInfo()
    }

    fun read() {
        log.info { "call read"}
        printTxInfo()
    }

    private fun printTxInfo() {
        val txActive = TransactionSynchronizationManager.isActualTransactionActive()
        log.info { "tx active=$txActive" }
        val readOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly()
        log.info { "tx readOnly=$readOnly" }
    }
}