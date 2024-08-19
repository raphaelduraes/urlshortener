package com.practice.urlshortener.controller;

import com.google.gson.JsonObject;
import com.practice.urlshortener.exception.UrlNotFoundException;
import com.practice.urlshortener.service.ShortenerService;
import org.apache.coyote.BadRequestException;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UrlShortenerController {

    @Autowired
    private ShortenerService shortenerService;

    @GetMapping("/{hash}")
    public ResponseEntity<String> getOriginalUrl(@PathVariable String hash) {
        try {
            String originalUrl = shortenerService.getOriginalUrl(hash);
            return new ResponseEntity<>(originalUrl, HttpStatus.OK);
        } catch(UrlNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<String> getShortUrl(@RequestBody String requestBody) throws BadRequestException {
        try {
            JSONObject body = new JSONObject(requestBody);
            String originalURl = body.get("originalUrl").toString();
            return new ResponseEntity<>(shortenerService.shortUrl(originalURl), HttpStatus.OK);
        } catch (JSONException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
