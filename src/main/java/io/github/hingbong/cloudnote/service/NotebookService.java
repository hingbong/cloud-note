package io.github.hingbong.cloudnote.service;

import io.github.hingbong.cloudnote.entity.Notebook;
import java.util.List;

public interface NotebookService {

  void addNotebook(String title, Integer uid);

  void modifyTitle(Integer nbId, String title, Integer uid);

  List<Notebook> findAllByUid(Integer uid);

  void delete(Integer uid, Integer nbId);
}
