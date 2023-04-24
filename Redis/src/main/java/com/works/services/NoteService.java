package com.works.services;

import com.works.entities.Note;
import com.works.models.RedisNote;
import com.works.redisrepositories.NoteRedisRepository;
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
    final NoteRedisRepository noteRedisRepository;

    public Note save( Note note ) {
        //cacheManager.getCache("note").clear();
        repository.save(note);
        RedisNote redisNote = new RedisNote();
        redisNote.setNid(note.getNid());
        redisNote.setTitle(note.getTitle());
        redisNote.setDetail(note.getDetail());
        noteRedisRepository.save(redisNote);
        return note;
    }

    //@Cacheable("note")
    public List<RedisNote> list() {
        List<RedisNote> ls = noteRedisRepository.findAll();
        return ls;
    }

}
