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
   * @param description notebook description
   * @param uid user id
   */
  void addNotebook(String title, String description, Integer uid);

  /**
   * change a new title
   *
   * @param nbId notebook id
   * @param title new title
   * @param uid user id
   */
  void modifyTitle(Integer nbId, String title, Integer uid);

  /**
   * update notebook description
   *
   * @param uid user id
   * @param description notebook description
   * @param nbId notebook id
   */
  void modifyDescription(Integer nbId, String description, Integer uid);

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

  /**
   * find notes by user and notebook
   *
   * @param uid user id
   * @param nbId notebook id
   * @return response
   */
  Notebook findByNbIdAndUid(Integer uid, Integer nbId);
}
