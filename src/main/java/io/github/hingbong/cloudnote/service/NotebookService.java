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
   */
  void addNotebook(String title, String description);

  /**
   * add a new notebook for register
   *
   * @param uid user id
   * @param title notebook title
   * @param description notebook description
   */
  void addNotebookForRegister(Integer uid, String title, String description);

  /**
   * change a new title
   *
   * @param nbId notebook id
   * @param title new title
   */
  void modifyTitle(Integer nbId, String title);

  /**
   * update notebook description
   *
   * @param description notebook description
   * @param nbId notebook id
   */
  void modifyDescription(Integer nbId, String description);

  /**
   * find all notebooks of a user
   *
   * @return all notebooks of a user
   */
  List<Notebook> findAllForCurrentUser();

  /**
   * delete a notebook
   *
   * @param nbId notebook id
   */
  void delete(Integer nbId);

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
   * @param nbId notebook id
   * @return response
   */
  Notebook findByNbIdAndCurrentUser(Integer nbId);
}
