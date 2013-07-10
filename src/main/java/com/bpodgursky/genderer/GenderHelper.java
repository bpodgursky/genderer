package com.bpodgursky.genderer;

import java.io.IOException;

public class GenderHelper {

  public static Gender getGender(DeducedName name) throws IOException {
    NameFreqCorpus inst = NameFreqCorpus.inst();

    if(name.getFirst() != null){
      String first = name.getFirst();

      Double maleFreq = inst.getMaleFreq(first);
      Double femaleFreq = inst.getFemaleFreq(first);

      if(maleFreq > 0.0 || femaleFreq > 0.0){
        if(maleFreq > femaleFreq){
          return Gender.MALE;
        }else{
          return Gender.FEMALE;
        }
      }
    }
    return null;
  }
}
