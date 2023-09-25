package hello.springtx.apply

import io.kotest.core.spec.style.ShouldSpec
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.stereotype.Service

@SpringBootTest
class InternalCallV1Test: ShouldSpec({

}) {
    @Service
    internal class CallService {
        fun external() {

        }
    }
}
