package com.bpodgursky.genderer;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class TestNameHelper {

  @Test
  public void test() throws IOException {

    assertEquals(new DeducedName("bob", null, "smith"), NameHelper.getName(Lists.newArrayList(
        "bob",
        "jebediah",
        "token",
        "smithly",
        "smith"
    )));

    assertEquals(new DeducedName("amy", null, "gomez"), NameHelper.getName(Lists.newArrayList(
        "amy",
        "jebediah",
        "token",
        "smithly",
        "gomez"
    )));

  }

}
