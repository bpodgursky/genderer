package com.bpodgursky.genderer;

import java.io.IOException;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestNameFreqCorpus {

  @Test
  public void testIt() throws IOException {

    NameFreqCorpus inst = NameFreqCorpus.inst();

    assertEquals(0.00016, inst.getFemaleFreq("abby"), .0001);
    assertEquals(0.0011, inst.getFemaleFreq("beth"), .0001);
    assertEquals(0.000030, inst.getFemaleFreq("kelsie"), .0001);

    assertEquals(0.00055, inst.getMaleFreq("bob"), .0001);
    assertEquals(0.00078, inst.getMaleFreq("ben"), .0001);
    assertEquals(.01404, inst.getMaleFreq("joseph"), .0001);

    assertEquals(new Integer(2376206), inst.getSurnameCount("smith"));
    assertEquals(new Integer(263590), inst.getSurnameCount("gomez"));

  }
}
