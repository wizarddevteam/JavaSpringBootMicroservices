package aforo255.com.msawsaccount.metrics;

import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@Component
public class IncreaseCounterAccount {

    private final Counter accountGetCounter;

    public IncreaseCounterAccount(MeterRegistry meterRegistry) {
        accountGetCounter = meterRegistry.counter("account.get");
    }

    public void increaseCounter() {
        accountGetCounter.increment();
    }

}
