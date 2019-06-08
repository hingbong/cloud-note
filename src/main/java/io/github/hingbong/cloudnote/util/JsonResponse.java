package io.github.hingbong.cloudnote.util;

import java.io.Serializable;
import java.util.Objects;

/**
 * a json response data
 *
 * @param <T> data's type
 * @author Hingbong
 */
public class JsonResponse<T> implements Serializable {

  private int code;
  private String msg;
  private T data;

  private JsonResponse(int code, String msg, T data) {
    this.code = code;
    this.msg = msg;
    this.data = data;
  }

  /**
   * new a json response entity
   *
   * @param code stand by the operation code
   * @param msg  success or failed's reasons
   * @param data when need data, send data
   * @param <T>  data's type
   * @return the whole entity
   */
  public static <T> JsonResponse<T> response(int code, String msg, T data) {
    return new JsonResponse<>(code, msg, data);
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    JsonResponse<?> that = (JsonResponse<?>) o;
    return Objects.equals(code, that.code)
        && Objects.equals(msg, that.msg)
        && Objects.equals(data, that.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, msg, data);
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("JsonResponse{");
    sb.append("code=").append(code);
    sb.append(", msg='").append(msg).append('\'');
    sb.append(", data=").append(data);
    sb.append('}');
    return sb.toString();
  }
}
