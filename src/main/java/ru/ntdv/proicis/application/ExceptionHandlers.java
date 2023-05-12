package ru.ntdv.proicis.application;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.execution.DataFetcherExceptionResolver;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public
class ExceptionHandlers implements DataFetcherExceptionResolver {
@Override
public
Mono<List<GraphQLError>> resolveException(Throwable exception, final DataFetchingEnvironment env) {

    log.debug("Exception name: {}", exception.getClass().getName());
    log.debug("Exception message: {}", exception.getMessage());

    if (exception instanceof ConstraintViolationException) {
        final var joinedErrorMessage = ((ConstraintViolationException) exception).getConstraintViolations().stream()
                                                                                 .map(ConstraintViolation::getMessage)
                                                                                 .collect(Collectors.joining("\n"));

        return Mono.fromCallable(() -> Collections.singletonList(
                GraphqlErrorBuilder.newError(env).errorType(ErrorType.ValidationError).message(joinedErrorMessage).build()));
    }

    return Mono.empty();
}
}
