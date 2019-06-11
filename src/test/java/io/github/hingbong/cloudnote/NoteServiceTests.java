package io.github.hingbong.cloudnote;


import io.github.hingbong.cloudnote.entity.Note;
import io.github.hingbong.cloudnote.service.NoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NoteServiceTests {

  private NoteService serivce;

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
  void setSerivce(NoteService serivce) {
    this.serivce = serivce;
  }
}
