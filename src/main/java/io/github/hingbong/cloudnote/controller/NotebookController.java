package io.github.hingbong.cloudnote.controller;

import io.github.hingbong.cloudnote.entity.Notebook;
import io.github.hingbong.cloudnote.service.NotebookService;
import io.github.hingbong.cloudnote.util.JsonResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * notebook controller
 *
 * @author Hingbong
 */
@RequestMapping("/notebook")
@RestController
public class NotebookController extends BaseController {

  private NotebookService notebookService;

  /**
   * add notebook
   *
   * @param title notebook title
   * @param description notebook description
   * @return response
   */
  @PostMapping
  public JsonResponse<Void> addNotebook(String title, String description) {
    notebookService.addNotebook(title, description);
    return JsonResponse.success("添加成功");
  }

  /**
   * change notebook title
   *
   * @param nbId notebook id
   * @param title new notebook title
   * @return response
   */
  @PutMapping("/title")
  public JsonResponse<Void> modifyTitle(Integer nbId, String title) {
    notebookService.modifyTitle(nbId, title);
    return JsonResponse.success("修改成功");
  }

  /**
   * change notebook description
   *
   * @param nbId nbId notebook id
   * @param description new notebook description
   * @return response
   */
  @PutMapping("/description")
  public JsonResponse<Void> modifyDescription(Integer nbId, String description) {
    notebookService.modifyDescription(nbId, description);
    return JsonResponse.success("修改成功");
  }

  /**
   * get notebooks of user
   *
   * @return response
   */
  @GetMapping
  public JsonResponse<List<Notebook>> findAllByUid() {
    return JsonResponse.success("获取成功", notebookService.findAllForCurrentUser());
  }

  /**
   * get one notebook of user
   *
   * @param nbId notebook id
   * @return response
   */
  @GetMapping("/{nbId}")
  public JsonResponse<Notebook> findByNbId(@PathVariable("nbId") Integer nbId) {
    Notebook notebook = notebookService.findByNbIdAndCurrentUser(nbId);
    return JsonResponse.success(notebook);
  }

  /**
   * delete a notebook
   *
   * @param nbId notebook id
   * @return response
   */
  @DeleteMapping("/{nbId}")
  public JsonResponse<Void> delete(@PathVariable Integer nbId) {
    notebookService.delete(nbId);
    return JsonResponse.success("删除成功");
  }

  @Autowired
  private void setNotebookService(NotebookService notebookService) {
    this.notebookService = notebookService;
  }
}
