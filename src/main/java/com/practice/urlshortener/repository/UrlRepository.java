package com.practice.urlshortener.repository;

import com.practice.urlshortener.model.Url;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepository extends CrudRepository<Url, Long> {
    Optional<Url> findByBase64Hash(String hash);
}
