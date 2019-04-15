package augustovictor.springstadsd.infra.statsd

import com.timgroup.statsd.StatsDClient
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import kotlin.system.measureTimeMillis

@Aspect
class MethodProfiler(val statsDClient: StatsDClient) {

    @Pointcut("execution(* augustovictor.springstadsd.HelloController.*(..))")
    fun restServiceMethods() {}

    @Around("restServiceMethods()")
    fun profile(pjp: ProceedingJoinPoint): Any {
        val output = measureTimeMillis { pjp.proceed() }

        val key = "${pjp.signature.declaringTypeName}.${pjp.signature.name}"

        statsDClient.recordExecutionTime(key, output)

        return output

    }
}
