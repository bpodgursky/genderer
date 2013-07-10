package com.bpodgursky.genderer;

import com.google.common.collect.Maps;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class FreqCorpus {
  private static final String SURNAMES = "com/bpodgursky/genderer/surnames.csv.gz";
  private static final String FIRST_FEMALE = "com/bpodgursky/genderer/dist.female.first.txt.gz";
  private static final String FIRST_MALE = "com/bpodgursky/genderer/dist.male.first.txt.gz";
  private static final String RESOURCE = "com/bpodgursky/genderer/SUBTLEXusrawdata.dat.gz";


  private static FreqCorpus inst;

  public static FreqCorpus inst() throws IOException {
    if(inst == null){
      inst = new FreqCorpus();
    }

    return inst;
  }

  private final Map<String, Integer> surnameToCount = Maps.newHashMap();
  private final Map<String, Double> maleNameToFreq = Maps.newHashMap();
  private final Map<String, Double> femaleNameToFreq = Maps.newHashMap();
  private final Map<String, Integer> wordFreqs = Maps.newHashMap();

  private final int surnameSum;
  private final int wordSum;

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

  public Double getSurnameFreq(String surname){
    Integer res = surnameToCount.get(surname.toLowerCase());
    if(res == null){
      return 0.0;
    }
    return res.doubleValue() / surnameSum;
  }

  public Double getWordFreq(String val) {
    Integer clean = wordFreqs.get(val.toLowerCase().trim());
    if (clean == null) {
      return 0.0;
    }
    return clean.doubleValue() / wordSum;
  }

  private static final double GIVEN_WEIGHT = 50.0;
  private static final double SURNAME_WEIGHT = 50.0;

  public Double getFreq(String val){
    double female = getFemaleFreq(val) * GIVEN_WEIGHT;
    double male = getMaleFreq(val) * GIVEN_WEIGHT;
    double surname = getSurnameFreq(val) * SURNAME_WEIGHT;

    double word = getWordFreq(val);

    return max(female, male, surname, word);
  }

  private static double max(double ... vals){
    double max = Double.NEGATIVE_INFINITY;
    for (double val : vals) {
      max = Math.max(val, max);
    }
    return max;
  }

  private FreqCorpus() throws IOException {

    Scanner surnameScan = CorpusHelper.openCorpus(SURNAMES);
    surnameScan.nextLine();

    int surnameSum = 0;
    while(surnameScan.hasNext()){
      String x = surnameScan.nextLine();
      String[] parts = x.split(",");

      String clean = parts[0].trim().toLowerCase();
      Integer count = Integer.parseInt(parts[2]);
      surnameSum += count;

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

    Scanner wordsScan = CorpusHelper.openCorpus(RESOURCE);
    wordsScan.nextLine();

    int wordSum = 0;
    while(wordsScan.hasNext()){
      String x = wordsScan.nextLine();
      String[] parts = x.split("\\s+");

      String clean = parts[0].trim().toLowerCase();
      Integer count = Integer.parseInt(parts[1]);
      wordSum += count;

      if(!wordFreqs.containsKey(clean)){
        wordFreqs.put(clean, 0);
      }

      wordFreqs.put(clean, wordFreqs.get(clean)+count);
    }

    this.surnameSum = surnameSum;
    this.wordSum = wordSum;
  }

}
