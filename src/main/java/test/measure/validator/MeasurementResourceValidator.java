package test.measure.validator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import test.measure.exception.InvalidRequestException;
import test.measure.rest.MeasurementResource;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class MeasurementResourceValidator implements ResourceValidator<MeasurementResource> {

    private final Validator validator;

    @Override
    public void validate(@Nullable MeasurementResource resource) {
        String errorMessage = validator.validate(resource)
                .stream()
                .map(this::errorMessage)
                .sorted()
                .collect(Collectors.joining("; "));
        if (! errorMessage.isEmpty()) {
            log.warn("Request validation constraints detected: {}", errorMessage);
            throw new InvalidRequestException(errorMessage);
        }
    }

    @Nonnull
    private String errorMessage(@Nonnull ConstraintViolation<MeasurementResource> constraint) {
        return constraint.getPropertyPath() + " " + constraint.getMessage();
    }

}
