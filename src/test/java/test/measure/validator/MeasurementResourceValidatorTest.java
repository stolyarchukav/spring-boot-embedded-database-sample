package test.measure.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import test.measure.exception.InvalidRequestException;
import test.measure.rest.MeasurementResource;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Collections;

import static org.mockito.Mockito.when;
import static test.measure.TestUtil.resource;

@RunWith(MockitoJUnitRunner.class)
public class MeasurementResourceValidatorTest {

    @Mock
    private Validator validator;

    @InjectMocks
    private MeasurementResourceValidator resourceValidator;

    @Mock
    private ConstraintViolation<MeasurementResource> constraintViolation;

    @Test
    public void validateSuccess() {
        resourceValidator.validate(resource());
    }

    @Test (expected = InvalidRequestException.class)
    public void validateFailed() {
        MeasurementResource resource = resource();
        when(validator.validate(resource)).thenReturn(Collections.singleton(constraintViolation));

        resourceValidator.validate(resource);
    }

}
