package org.zalando.riptide.httpclient;

import org.apache.http.client.HttpClient;
import org.springframework.core.task.AsyncListenableTaskExecutor;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.AsyncClientHttpRequest;
import org.springframework.http.client.AsyncClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import java.io.IOException;
import java.net.URI;

public class RestAsyncClientHttpRequestFactory implements ClientHttpRequestFactory, AsyncClientHttpRequestFactory {

    private final ClientHttpRequestFactory factory;
    private final AsyncListenableTaskExecutor executor;

    public RestAsyncClientHttpRequestFactory(final HttpClient client, final AsyncListenableTaskExecutor executor) {
        this.factory = new HttpComponentsClientHttpRequestFactory(client);
        this.executor = executor;
    }

    @Override
    public ClientHttpRequest createRequest(final URI uri, final HttpMethod method) throws IOException {
        return factory.createRequest(uri, method);
    }

    @Override
    public AsyncClientHttpRequest createAsyncRequest(final URI uri, final HttpMethod method) throws IOException {
        return new RestAsyncClientHttpRequest(factory.createRequest(uri, method), executor);
    }

}
