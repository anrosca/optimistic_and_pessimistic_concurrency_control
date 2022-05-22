package inc.evil.clinic.appointment.web;

import inc.evil.clinic.common.AbstractWebIntegrationTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AppointmentControllerTest extends AbstractWebIntegrationTest {
    private static final int NUMBER_OF_CONCURRENT_USERS = 5;

    private final CountDownLatch startLatch = new CountDownLatch(1);
    private final CountDownLatch requestLatch = new CountDownLatch(NUMBER_OF_CONCURRENT_USERS);
    private final ExecutorService requestPool = Executors.newFixedThreadPool(NUMBER_OF_CONCURRENT_USERS);

    @Test
    public void shouldBeAbleToCreateAppointments() {
        IntStream.rangeClosed(1, NUMBER_OF_CONCURRENT_USERS).forEach((item) -> {
            requestPool.submit(() -> {
                await(startLatch);
                log.info("Executing create appointment HTTP request");
                String payload = """
                {
                   "doctorId": "620e11c0-7d59-45be-85cc-0dc146532e78",
                   "patientId": "f44e4567-ef9c-12d3-a45b-52661417400a",
                   "startDate": "2022-05-23T16:00",
                   "endDate": "2022-05-23T17:00",
                   "operation": "Annual physical",
                   "details": "New patient"
                 }
                """;
                RequestEntity<String> request = makeRequestFor("/api/v1/appointments/", HttpMethod.POST, payload);

                ResponseEntity<String> response = restTemplate.exchange(request, String.class);

                log.info("Received HTTP status code: {}", response.getStatusCode().value());
                requestLatch.countDown();
            });
        });

        startLatch.countDown();
        await(requestLatch);

        RequestEntity<AppointmentResponse[]> allAppointmentsRequest = makeRequestFor("/api/v1/appointments/", HttpMethod.GET);
        ResponseEntity<AppointmentResponse[]> allAppointmentsResponse = restTemplate.exchange(allAppointmentsRequest, AppointmentResponse[].class);
        assertThat(allAppointmentsResponse.getStatusCode().value()).isEqualTo(HttpStatus.OK.value());
        log.info("All appointments: {}", Arrays.toString(allAppointmentsResponse.getBody()));
        assertThat(allAppointmentsResponse.getBody().length).isEqualTo(1);
    }

    @AfterEach
    public void tearDown() throws InterruptedException {
        requestPool.shutdown();
        requestPool.awaitTermination(10, TimeUnit.SECONDS);
        requestPool.shutdownNow();
    }

    private void await(CountDownLatch latch) {
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
