package com.sapiofan.computers.services.impl;

import com.sapiofan.computers.dao.NoteDao;
import com.sapiofan.computers.entity.Computer;
import com.sapiofan.computers.entity.Note;
import com.sapiofan.computers.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteDao noteDao;

    @Override
    public Note addNote(Note note) {
        if(note == null) {
            return null;
        }

        return noteDao.save(note);
    }
}
