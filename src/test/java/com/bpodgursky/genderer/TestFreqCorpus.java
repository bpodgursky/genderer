package com.bpodgursky.genderer;

import org.junit.Test;

import static org.junit.Assert.*;

import java.io.IOException;

public class TestFreqCorpus {

  @Test
  public void testIt() throws IOException {

    FreqCorpus inst = FreqCorpus.inst();

    assertEquals(.0000979, inst.getWordFreq("bEn"), .0000001);
    assertEquals(.0000979, inst.getWordFreq("ben"), .0000001);

    assertEquals(.000269667, inst.getWordFreq("phONe  "), .0000001);
    assertEquals(0,0, inst.getWordFreq("afasdfsafdsafafphONe  "));

  }


  @Test
  public void testIt2() throws IOException {

    FreqCorpus inst = FreqCorpus.inst();

    assertEquals(0.00016, inst.getFemaleFreq("abby"), .0001);
    assertEquals(0.0011, inst.getFemaleFreq("beth"), .0001);
    assertEquals(0.000030, inst.getFemaleFreq("kelsie"), .0001);

    assertEquals(0.00055, inst.getMaleFreq("bob"), .0001);
    assertEquals(0.00078, inst.getMaleFreq("ben"), .0001);
    assertEquals(.01404, inst.getMaleFreq("joseph"), .0001);

    assertEquals(0.009814123053, inst.getSurnameFreq("smith"), .000000001);
    assertEquals(0.001088670212526276, inst.getSurnameFreq("gomez"), .000000001);

  }
}
