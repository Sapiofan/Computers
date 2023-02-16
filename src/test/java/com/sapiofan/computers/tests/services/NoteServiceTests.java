package com.sapiofan.computers.tests.services;

import com.sapiofan.computers.dao.NoteDao;
import com.sapiofan.computers.entity.Note;
import com.sapiofan.computers.services.impl.NoteServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NoteServiceTests {
    @InjectMocks
    private NoteServiceImpl noteService;

    @Mock
    private NoteDao noteDao;

    @Test
    public void addNoteSuccessfulTest() {
        Note note = new Note();
        note.setPhone("+38 050 500 5000");
        note.setAddress("address");

        when(noteDao.save(note)).thenReturn(note);

        Note note1 = noteService.addNote(note);

        Assertions.assertEquals(note.getId(), note1.getId());
        Assertions.assertEquals(note.getPhone(), note1.getPhone());
    }

    @Test
    public void addNoteFailureTest() {
        Assertions.assertNull(noteService.addNote(null));
    }
}
