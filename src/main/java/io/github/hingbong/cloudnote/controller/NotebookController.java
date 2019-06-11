package io.github.hingbong.cloudnote.controller;

import io.github.hingbong.cloudnote.entity.Notebook;
import io.github.hingbong.cloudnote.service.NotebookService;
import io.github.hingbong.cloudnote.util.JsonResponse;
import java.util.List;
import javax.servlet.http.HttpSession;
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

  @PostMapping
  public JsonResponse<Void> addNotebook(String title, HttpSession session) {
    Integer uid = getUidFromSession(session);
    notebookService.addNotebook(title, uid);
    return JsonResponse.response(SUCCESS, "添加成功", null);
  }

  @PutMapping("/{nbId}/{title}")
  public JsonResponse<Void> modifyTitle(
      @PathVariable("nbId") Integer nbId,
      @PathVariable("title") String title,
      HttpSession session) {
    Integer uid = getUidFromSession(session);
    notebookService.modifyTitle(nbId, title, uid);
    return JsonResponse.response(SUCCESS, "修改成功", null);
  }

  @GetMapping
  public JsonResponse<List<Notebook>> findAllByUid(HttpSession session) {
    Integer uid = getUidFromSession(session);
    return JsonResponse.response(SUCCESS, "获取成功", notebookService.findAllByUid(uid));
  }

  @DeleteMapping("/{nbId}")
  public JsonResponse<Void> delete(@PathVariable Integer nbId, HttpSession session) {
    Integer uid = getUidFromSession(session);
    notebookService.delete(uid, nbId);
    return JsonResponse.response(SUCCESS, "删除成功", null);
  }

  @Autowired
  public void setNotebookService(NotebookService notebookService) {
    this.notebookService = notebookService;
  }
}
