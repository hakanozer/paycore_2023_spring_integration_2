package com.works.restcontrollers;

import com.works.entities.Note;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.works.services.NoteService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/note")
public class NoteRestController {

    final NoteService noteService;

    @PostMapping("/save")
    public Note save(@RequestBody Note note) {
        return noteService.save(note);
    }

    @GetMapping("/list")
    public List<Note> list() {
        return noteService.list();
    }

}
