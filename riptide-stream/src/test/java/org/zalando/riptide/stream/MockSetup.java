package org.zalando.riptide.stream;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.AsyncRestTemplate;
import org.zalando.riptide.Rest;

public final class MockSetup {

    private final MockRestServiceServer server;
    private final Rest rest;

    public MockSetup(final String baseUrl, final Iterable<HttpMessageConverter<?>> converters) {
        final AsyncRestTemplate template = new AsyncRestTemplate();
        this.server = MockRestServiceServer.createServer(template);
        this.rest = Rest.builder()
                .requestFactory(template.getAsyncRequestFactory())
                .converters(converters)
                .baseUrl(baseUrl)
                .build();
    }

    public MockRestServiceServer getServer() {
        return server;
    }

    public Rest getRest() {
        return rest;
    }

}
