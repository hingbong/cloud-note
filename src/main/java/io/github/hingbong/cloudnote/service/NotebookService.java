package io.github.hingbong.cloudnote.service;

import io.github.hingbong.cloudnote.entity.Notebook;
import java.util.List;

/**
 * notebook service
 *
 * @author Hingbong
 */
public interface NotebookService {

  /**
   * add a new notebook
   *
   * @param title notebook title
   * @param uid   user id
   */
  void addNotebook(String title, Integer uid);

  /**
   * change a new title
   *
   * @param nbId notebook id
   * @param title new title
   * @param uid user id
   */
  void modifyTitle(Integer nbId, String title, Integer uid);

  /**
   * find all notebooks of a user
   *
   * @param uid user id
   * @return all notebooks of a user
   */
  List<Notebook> findAllByUid(Integer uid);

  /**
   * delete a notebook
   *
   * @param uid user id
   * @param nbId notebook id
   */
  void delete(Integer uid, Integer nbId);

  /**
   * find notebook by notebook id
   *
   * @param nbId notebook id
   * @return notebook which is found
   */
  Notebook findByNbId(Integer nbId);
}
