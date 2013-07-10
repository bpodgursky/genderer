package com.bpodgursky.genderer;

import org.junit.Test;

import static org.junit.Assert.*;

import java.io.IOException;

public class TestFreqCorpus {

  @Test
  public void testIt() throws IOException {

    WordFreqCorpus inst = WordFreqCorpus.inst();

    assertEquals(new Integer(4994), inst.getVal("bEn"));
    assertEquals(new Integer(4994), inst.getVal("ben"));

    assertEquals(new Integer(13756), inst.getVal("phONe  "));
    assertNull(inst.getVal("afasdfsafdsafafphONe  "));

  }
}
