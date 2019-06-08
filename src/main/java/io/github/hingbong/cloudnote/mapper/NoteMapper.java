package io.github.hingbong.cloudnote.mapper;

import io.github.hingbong.cloudnote.entity.Note;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteMapper {

  Integer insert(Note note);

  List<Note> findAll(Integer nbId);

  Note getOneByNid(Integer nid);

  Integer markIsDeleted(Integer nid);

  Integer updateNote(Note note);
}
