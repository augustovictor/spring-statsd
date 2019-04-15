package augustovictor.springstadsd

import com.timgroup.statsd.StatsDClient
import org.springframework.http.HttpStatus.OK
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.TimeUnit

@RestController
class HelloController(val statsDClient: StatsDClient) {
    @GetMapping("/hello")
    @ResponseStatus(OK)
    fun sayHello(): String {
        statsDClient.incrementCounter("helloController.hello")
        return "Hello!"
    }

    @GetMapping("/bye")
    @ResponseStatus(OK)
    fun bye(): String {
        statsDClient.incrementCounter("helloController.bye")
        return "bye!"
    }

    @GetMapping("/count")
    @ResponseStatus(OK)
    fun count(): String {
//        statsDClient.recordExecutionTime()
        TimeUnit.SECONDS.sleep(10)
        return "Done counting to 10s"
    }
}
