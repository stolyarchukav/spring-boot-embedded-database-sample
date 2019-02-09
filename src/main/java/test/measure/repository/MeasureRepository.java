package test.measure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Nonnull;
import java.util.List;

@Repository
public interface MeasureRepository extends JpaRepository<Measurement, Long> {

    @Nonnull
    List<Measurement> findByUserIdOrderByDateDesc(@Nonnull String userId);

}
