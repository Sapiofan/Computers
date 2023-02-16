package com.sapiofan.computers.dao;

import com.sapiofan.computers.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteDao extends JpaRepository<Note, Long> {
}
