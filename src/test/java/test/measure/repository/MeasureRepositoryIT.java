package test.measure.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MeasureRepositoryIT {

    private static final String USER_ID = "user_1";

    @Autowired
    private MeasureRepository repository;

    @Test
    public void testSave() {
        Measurement measurement = measurement();
        Measurement stored = repository.save(measurement);

        assertEquals(BigDecimal.ONE, stored.getGas());
        assertEquals(BigDecimal.TEN, stored.getColdWater());
        assertEquals(new BigDecimal("123.321"), stored.getHotWater());
        assertNotNull(stored.getId());
        assertEquals(measurement.getDate(), stored.getDate());
    }

    @Test
    public void testFindByUserIdOrderByDateDesc() {
        repository.saveAll(Arrays.asList(measurement(), measurement(), measurement("another_user"), measurement()));
        List<Measurement> measurements = repository.findByUserIdOrderByDateDesc(USER_ID);
        assertEquals(3, measurements.size());
    }

    private Measurement measurement(String userId) {
        return Measurement.builder()
                .date(new Date())
                .userId(userId)
                .gas(BigDecimal.ONE)
                .coldWater(BigDecimal.TEN)
                .hotWater(new BigDecimal("123.321"))
                .build();
    }

    private Measurement measurement() {
        return measurement(USER_ID);
    }

}
