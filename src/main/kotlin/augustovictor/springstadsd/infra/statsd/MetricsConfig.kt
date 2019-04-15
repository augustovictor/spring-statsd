package augustovictor.springstadsd.infra.statsd

import com.timgroup.statsd.NonBlockingStatsDClient
import com.timgroup.statsd.StatsDClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MetricsConfig {
    @Value("\${metrics.statsd.host}") lateinit var host: String
    @Value("\${metrics.statsd.port}") lateinit var port: String
    @Value("\${metrics.statsd.prefix}") lateinit var prefix: String

    @Bean
    fun statsDClient(): StatsDClient {
        return NonBlockingStatsDClient(prefix, host, port.toInt())
    }
}
