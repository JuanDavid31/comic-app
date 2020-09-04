package com.example.technicaltest.network;

import com.example.technicaltest.R;
import com.example.technicaltest.network.property.MovieProperty;
import com.example.technicaltest.network.property.ResponseProperty;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

import kotlin.text.Charsets;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okio.BufferedSource;
import okio.Okio;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class ComicServiceTest {

    MockWebServer mockWebServer;
    ComicService comicService;

    @Before
    public void before(){
        mockWebServer = new MockWebServer();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .client(getClient())
                .baseUrl(mockWebServer.url("").toString())
                .build();
        enqueueResponse();
        comicService = retrofit.create(ComicService.class);
    }

    @After
    public void after() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    public void requestTest() throws IOException, InterruptedException {
        Call<ResponseProperty> call = comicService.getMovies();
        Response<ResponseProperty> response = call.execute();
        assertNotNull(response);
        assertEquals("/movies/?api_key=&format=", mockWebServer.takeRequest().getPath());
    }

    @Test
    public void responseMovie1Test() throws IOException, InterruptedException {
        List<MovieProperty> movies = Objects.requireNonNull(comicService.getMovies().execute().body()).movies;
        MovieProperty movie = movies.get(0);
        assertNotNull(movie);
        assertEquals(1, movie.id);
        assertEquals("Watchmen", movie.name);
        assertEquals("R", movie.rating);
        assertEquals("130000000", movie.budget);
        assertEquals("107509799", movie.boxOfficeRevenue);
        assertEquals("185258983", movie.totalRevenue);
    }

    @Test
    public void responseMovie2Test() throws IOException, InterruptedException {
        List<MovieProperty> movies = Objects.requireNonNull(comicService.getMovies().execute().body()).movies;
        MovieProperty movie = movies.get(1);
        assertNotNull(movie);
        assertEquals(2, movie.id);
        assertEquals("Superman", movie.name);
        assertEquals("PG", movie.rating);
        assertEquals("55000000", movie.budget);
        assertEquals("134218018", movie.boxOfficeRevenue);
        assertEquals("300218018", movie.totalRevenue);
    }

    private void enqueueResponse() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("api-response/movies.json");
        BufferedSource source = Okio.buffer(Okio.source(inputStream));
        MockResponse mockResponse = new MockResponse();

        try {
            mockWebServer.enqueue(mockResponse.setBody(source.readString(Charsets.UTF_8)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private OkHttpClient getClient(){
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.addInterceptor(chain -> {
            Request original = chain.request();
            HttpUrl originalHttpUrl = original.url();

            HttpUrl url = originalHttpUrl.newBuilder()
                    .addQueryParameter("api_key", "")
                    .addQueryParameter("format", "")
                    .build();

            Request.Builder requestBuilder = original.newBuilder()
                    .url(url);

            Request request = requestBuilder.build();
            return chain.proceed(request);
        });

        return clientBuilder.build();
    }
}
