package cn.dx.async.method.invocation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.concurrent.Callable;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author gudongxian
 * @date 2022/1/10
 */
class ThreadAsyncExecutorTest {
    @Captor
    private ArgumentCaptor<Optional<Exception>> optionalCaptor;

    @Mock
    private Callable<Object> task;

    @Mock
    private AsyncCallback<Object> callback;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSuccessfulTaskWithoutCallback() throws Exception {
        assertTimeout(ofMillis(3000), () -> {
            final AsyncExecutor executor = new ThreadAsyncExecutor();

            final Object result = new Object();
            when(task.call()).thenReturn(result);

            final AsyncResult<Object> asyncResult = executor.startProcess(task);
            assertNotNull(asyncResult);
            asyncResult.await(); // Prevent timing issues, and wait until the result is available
            assertTrue(asyncResult.isCompleted());

            verify(task, times(1)).call();

            assertSame(result, asyncResult.getValue());
        });
    }

    @Test
    void testSuccessfulTaskWithCallback() throws Exception {
        assertTimeout(ofMillis(3000), () -> {
            final AsyncExecutor executor = new ThreadAsyncExecutor();
            final Object result = new Object();
            when(task.call()).thenReturn(result);

            final AsyncResult<Object> asyncResult = executor.startProcess(task, callback);
            assertNotNull(asyncResult);
            asyncResult.await();
            assertTrue(asyncResult.isCompleted());

            verify(task, times(1)).call();

            verify(callback, times(1)).onComplete(eq(result), optionalCaptor.capture());

            Optional<Exception> optionalException = optionalCaptor.getValue();
            assertNotNull(optionalException);
            assertFalse(optionalException.isPresent());

            assertSame(result, asyncResult.getValue());
        });
    }
}