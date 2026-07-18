import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

class LoggingExtension implements BeforeEachCallback, AfterEachCallback {

    private static final ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create(LoggingExtension.class);
    private static final String START_TIME_KEY = "startTime";

    @Override
    public void beforeEach(ExtensionContext context) {
        long startTime = System.nanoTime();
        context.getStore(NAMESPACE).put(START_TIME_KEY, startTime);
        System.out.println("Starting test: " + context.getDisplayName() + " at " + startTime);
    }

    @Override
    public void afterEach(ExtensionContext context) {
        Long startTime = context.getStore(NAMESPACE).remove(START_TIME_KEY, Long.class);
        long endTime = System.nanoTime();
        long durationMillis = startTime == null ? 0L : (endTime - startTime) / 1_000_000L;
        System.out.println("Finished test: " + context.getDisplayName() + " in " + durationMillis + " ms");
    }
}