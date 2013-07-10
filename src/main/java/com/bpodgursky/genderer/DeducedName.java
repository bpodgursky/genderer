package com.bpodgursky.genderer;

import org.json.JSONException;
import org.json.JSONObject;

public class DeducedName {

  private final String first;
  private final String middle;
  private final String last;

  public DeducedName(String first, String middle, String last) {
    this.first = first;
    this.middle = middle;
    this.last = last;
  }

  public String getFirst() {
    return first;
  }

  public String getMiddle() {
    return middle;
  }

  public String getLast() {
    return last;
  }

  public JSONObject toJson() throws JSONException {
    return new JSONObject()
        .put("first", first)
        .put("middle", middle)
        .put("last", last);
  }

  @Override
  public String toString() {
    return "DeducedName{" +
        "first='" + first + '\'' +
        ", middle='" + middle + '\'' +
        ", last='" + last + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof DeducedName)) return false;

    DeducedName that = (DeducedName) o;

    if (first != null ? !first.equals(that.first) : that.first != null) return false;
    if (last != null ? !last.equals(that.last) : that.last != null) return false;
    if (middle != null ? !middle.equals(that.middle) : that.middle != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = first != null ? first.hashCode() : 0;
    result = 31 * result + (middle != null ? middle.hashCode() : 0);
    result = 31 * result + (last != null ? last.hashCode() : 0);
    return result;
  }
}
