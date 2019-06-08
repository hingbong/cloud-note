package io.github.hingbong.cloudnote;


import io.github.hingbong.cloudnote.entity.Note;
import io.github.hingbong.cloudnote.service.NoteSerivce;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NoteServiceTests {

  private NoteSerivce serivce;

  @Test
  void add() {
    Note note = new Note();
    note.setIsDeleted(0);
    note.setTitle("笔记１");
    note.setContent("正文１");
    note.setIsShared(0);
    note.setNbId(1);
    serivce.addNote(1, note);
  }

  @Autowired
  void setSerivce(NoteSerivce serivce) {
    this.serivce = serivce;
  }
}
