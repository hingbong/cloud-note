package io.github.hingbong.cloudnote;

import io.github.hingbong.cloudnote.entity.Notebook;
import io.github.hingbong.cloudnote.mapper.NotebookMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NotebookMapperTests {

  private NotebookMapper notebookMapper;

  @Test
  void insert() {
    Notebook notebook = new Notebook();
    notebook.setNbId(1);
    notebook.setTitle("记事本１");
    notebook.setUid(5);
    Integer insert = notebookMapper.insert(notebook);
    System.err.println(insert);
  }

  @Autowired
  public void setNotebookMapper(NotebookMapper notebookMapper) {
    this.notebookMapper = notebookMapper;
  }
}
