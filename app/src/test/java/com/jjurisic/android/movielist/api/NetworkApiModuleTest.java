package com.jjurisic.android.movielist.api;

import com.jjurisic.android.movielist.di.modules.MoviesApiModule;
import com.squareup.okhttp.OkHttpClient;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import retrofit.Endpoint;
import retrofit.RequestInterceptor;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Josip Jurisic
 */
public class NetworkApiModuleTest {

    private MoviesApiModule networkApiModule;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        networkApiModule = new MoviesApiModule();
    }

    @Test
    public void testProvideEndpoint() throws Exception {
        assertNotNull(networkApiModule.provideEndpoint());
    }

    @Test
    public void testProvideGsonConverter() throws Exception {
        assertNotNull(networkApiModule.provideGsonConverter());
    }

    @Test
    public void testProvideOkHttpClient() throws Exception {
        assertNotNull(networkApiModule.provideOkHttpClient());
    }

    @Test
    public void testProvideOkClient() throws Exception {
        OkHttpClient okHttpClient = Mockito.mock(OkHttpClient.class);
        assertNotNull(networkApiModule.provideOkClient(okHttpClient));
    }

    @Test
    public void testProvideClientId() throws Exception {
        assertNotNull(networkApiModule.provideClientId());
    }

    @Test
    public void testProvideRequestInterceptor() throws Exception {
        String apiKey = "apiKey";
        assertNotNull(networkApiModule.provideRequestInterceptor(apiKey));
    }

    @Test
    public void testProvideRestAdapter() throws Exception {
        Endpoint endpoint = Mockito.mock(Endpoint.class);
        RequestInterceptor requestInterceptor = Mockito.mock(RequestInterceptor.class);
        GsonConverter gsonConverter = Mockito.mock(GsonConverter.class);
        OkClient okClient = Mockito.mock(OkClient.class);
        assertNotNull(networkApiModule.provideRestAdapter(endpoint, requestInterceptor, gsonConverter, okClient));
    }
}