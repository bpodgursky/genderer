package com.bpodgursky.genderer;

import com.google.common.collect.Maps;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class NameFreqCorpus {
  private static final String SURNAMES = "com/bpodgursky/genderer/surnames.csv.gz";
  private static final String FIRST_FEMALE = "com/bpodgursky/genderer/dist.female.first.txt.gz";
  private static final String FIRST_MALE = "com/bpodgursky/genderer/dist.male.first.txt.gz";

  private static NameFreqCorpus inst;

  public static NameFreqCorpus inst() throws IOException {
    if(inst == null){
      inst = new NameFreqCorpus();
    }

    return inst;
  }

  private final Map<String, Integer> surnameToCount = Maps.newHashMap();
  private final Map<String, Double> maleNameToFreq = Maps.newHashMap();
  private final Map<String, Double> femaleNameToFreq = Maps.newHashMap();


  public Double getMaleFreq(String name){
    Double aDouble = maleNameToFreq.get(name.toLowerCase());
    if(aDouble == null){
      return 0.0;
    }
    return aDouble;
  }

  public Double getFemaleFreq(String name){
    Double res = femaleNameToFreq.get(name.toLowerCase());
    if(res == null){
      return 0.0;
    }
    return res;
  }

  public Integer getSurnameCount(String surname){
    Integer res = surnameToCount.get(surname.toLowerCase());
    if(res == null){
      return 0;
    }
    return res;
  }

  private NameFreqCorpus() throws IOException {

    Scanner surnameScan = CorpusHelper.openCorpus(SURNAMES);
    surnameScan.nextLine();

    while(surnameScan.hasNext()){
      String x = surnameScan.nextLine();
      String[] parts = x.split(",");

      String clean = parts[0].trim().toLowerCase();
      Integer count = Integer.parseInt(parts[2]);

      if(!surnameToCount.containsKey(clean)){
        surnameToCount.put(clean, 0);
      }
      surnameToCount.put(clean, surnameToCount.get(clean)+count);
    }

    Scanner maleScan = CorpusHelper.openCorpus(FIRST_MALE);
    while(maleScan.hasNext()){
      String x = maleScan.nextLine();
      String[] parts = x.split("\\s+");

      String clean = parts[0].trim().toLowerCase();
      Double count = Double.parseDouble(parts[1])*.01;

      if(!maleNameToFreq.containsKey(clean)){
        maleNameToFreq.put(clean, 0.0);
      }
      maleNameToFreq.put(clean, maleNameToFreq.get(clean)+count);
    }

    Scanner femaleScan = CorpusHelper.openCorpus(FIRST_FEMALE);
    while(femaleScan.hasNext()){
      String x = femaleScan.nextLine();
      String[] parts = x.split("\\s+");

      String clean = parts[0].trim().toLowerCase();
      Double count = Double.parseDouble(parts[1])*.01;

      if(!femaleNameToFreq.containsKey(clean)){
        femaleNameToFreq.put(clean, 0.0);
      }
      femaleNameToFreq.put(clean, femaleNameToFreq.get(clean)+count);
    }
  }

}
