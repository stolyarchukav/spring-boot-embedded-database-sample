package test.measure.converter;

import javax.annotation.Nonnull;

public interface Converter<E, R> {

    @Nonnull
    R toResource(@Nonnull E measurement);

    @Nonnull
    E toEntity(@Nonnull R resource);

}
