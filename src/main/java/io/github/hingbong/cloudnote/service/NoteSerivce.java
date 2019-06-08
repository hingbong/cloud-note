package io.github.hingbong.cloudnote.service;

import io.github.hingbong.cloudnote.entity.Note;
import java.util.List;

public interface NoteSerivce {

  void addNote(Integer uid, Note note);

  List<Note> getNoteByNotebook(Integer uid, Integer nbId);

  Note getNoteByNid(Integer uid, Integer nid);

  void modifyNote(Integer uid, Note note);
}
