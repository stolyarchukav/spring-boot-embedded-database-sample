package test.measure.validator;

import javax.annotation.Nullable;

public interface ResourceValidator<T> {

    void validate(@Nullable T resource);

}
