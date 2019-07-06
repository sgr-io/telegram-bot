package io.sgr.telegram.bot.api.http;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import io.sgr.telegram.bot.api.models.http.ApiResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.CompletableFuture;

@RunWith(MockitoJUnitRunner.class)
public class CompletableFutureBasedCallAdapterTest<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompletableFutureBasedCallAdapterTest.class);

    @Mock
    private Call<ApiResponse<T>> mockCall;

    @Test
    public void testAdapt() {
        Type returnType = new ParameterizedType() {
            @Override
            public Type[] getActualTypeArguments() {
                return new Type[]{String.class};
            }

            @Override
            public Type getRawType() {
                return CompletableFuture.class;
            }

            @Override
            public Type getOwnerType() {
                return Object.class;
            }
        };
        CompletableFutureBasedCallAdapter<T> adapter = new CompletableFutureBasedCallAdapter<>(returnType, false, LOGGER);
        assertEquals(returnType, adapter.responseType());

        CompletableFuture<T> future = adapter.adapt(mockCall);
        verify(mockCall, times(1)).enqueue(any());
        future.cancel(true);
        verify(mockCall, times(1)).cancel();
        future.cancel(false);
        verify(mockCall, times(1)).cancel();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructWithoutResponseTye() {
        new CompletableFutureBasedCallAdapter(null, true, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructWithoutLogger() {
        new CompletableFutureBasedCallAdapter(Object.class, true, null);
    }

}