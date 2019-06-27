package io.github.hingbong.cloudnote;

import io.github.hingbong.cloudnote.entity.Note;
import io.github.hingbong.cloudnote.mapper.NoteMapper;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NoteMapperTests {

  private NoteMapper noteMapper;

  @Test
  void insert() {
    Note note = new Note();
    note.setIsDeleted(0);
    note.setTitle("笔记１");
    note.setContent("正文１");
    note.setIsShared(0);
    note.setNbId(1);
    Integer insert = noteMapper.insert(note);
    System.err.println(insert);
  }

  @Test
  void all() {
    List<Note> allTitle = noteMapper.findAllInANotebook(1);
    System.out.println(allTitle);
  }

  @Test
  void getOne() {
    Note note = noteMapper.getOneByNid(1);
    System.out.println(note);
  }

  @Test
  void delete() {
    Integer deleted = noteMapper.markIsDeleted(1);
    System.err.println(deleted);
  }

  @Autowired
  void setNoteMapper(NoteMapper noteMapper) {
    this.noteMapper = noteMapper;
  }
}
