package com.assessment.presentation.validation

import com.assessment.exception.ErrorDetail
import com.assessment.exception.ValidationException
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.validation.BindingResult
import java.util.logging.Logger

@Aspect
@Component
class BindingResultValidationAspect {
    private val log = LoggerFactory.getLogger(javaClass)

    @Around("@annotation(com.assessment.presentation.validation.ValidateBindingResult) && args(..,bindingResult)")
    fun validateBindingResult(joinPoint: ProceedingJoinPoint, bindingResult: BindingResult): Any {
        if (bindingResult.hasErrors()) {
            val errors = bindingResult.fieldErrors.map {
                ErrorDetail(
                    field = it.field,
                    value = it.rejectedValue.toString(),
                    reason = it.defaultMessage.toString()
                )
            }

            log.info("검색 요청 파라미터 오류: {}", errors)

            throw ValidationException(errors = errors)
        }

        return joinPoint.proceed()
    }
}
