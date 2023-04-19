package com.works.services;

import com.works.entities.Note;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.works.repositories.NoteRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {

    final NoteRepository repository;
    final CacheManager cacheManager;

    public Note save( Note note ) {
        cacheManager.getCache("note").clear();
        return repository.save(note);
    }

    @Cacheable("note")
    public List<Note> list() {
        return repository.findAll();
    }

}
