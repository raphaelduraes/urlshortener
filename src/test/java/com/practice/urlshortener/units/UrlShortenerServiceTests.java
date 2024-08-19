package com.practice.urlshortener.units;

import com.practice.urlshortener.exception.UrlNotFoundException;
import com.practice.urlshortener.model.Url;
import com.practice.urlshortener.repository.UrlRepository;
import com.practice.urlshortener.service.UrlShortenerService;
import org.apache.commons.validator.routines.UrlValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.env.Environment;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UrlShortenerServiceTests {
    @Mock
    private UrlRepository urlRepository;

    @Mock
    private UrlValidator urlValidator;

    @Mock
    private Environment env;

    @InjectMocks
    private UrlShortenerService urlShortenerService;

    @Test
    public void getOriginalUrlTest() {
       String hash = "1g24g234h241v23b123b23n5j";
        Url url = new Url(hash, "http://www.google.com");
        when(urlRepository.findById(hash)).thenReturn(Optional.of(url));
        String result = urlShortenerService.getOriginalUrl(hash);
        assertEquals(result, url.getOriginalUrl());
    }

    @Test
    public void getOriginalUrlTest_Exception() {
        assertThrows(UrlNotFoundException.class, () -> urlShortenerService.getOriginalUrl("invalid_hashing"));
    }

    @Test
    public void shortUrlTest() {
        String url = "http://www.google.com";
        when(urlValidator.isValid(url)).thenReturn(true);
        when(env.getProperty("domain.name")).thenReturn("localhost");
        String result = urlShortenerService.shortUrl(url);
        assertNotNull(result);
    }

    @Test
    public void shortUrlTest_ExceptionInvalidUrl() {
        assertThrows(RuntimeException.class,()-> urlShortenerService.shortUrl("http:wwwgooglecom"));
    }

}
