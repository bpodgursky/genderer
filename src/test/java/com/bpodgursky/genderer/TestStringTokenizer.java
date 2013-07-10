package com.bpodgursky.genderer;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class TestStringTokenizer {

  @Test
  public void testIt() throws IOException {

    assertEquals(Lists.newArrayList("wow", "just", "wow"), StringTokenizer.getTokens("wowjustwow"));
    assertEquals(Lists.newArrayList("something"), StringTokenizer.getTokens("something"));
    assertEquals(Lists.newArrayList("really", "something", "cool"), StringTokenizer.getTokens("reallysomethingcool"));
    assertEquals(Lists.newArrayList("eddie", "siegel", "the", "great", "dude"), StringTokenizer.getTokens("eddiesiegelthegreat42dude"));

  }

}
