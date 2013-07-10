package com.bpodgursky.genderer;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class NameHelper {

  public static DeducedName getName(List<String> tokens) throws IOException {
    NameFreqCorpus inst = NameFreqCorpus.inst();

    Set<StringScore> firsts = Sets.newTreeSet();
    Set<StringScore> lasts = Sets.newTreeSet();

    for (String token : tokens) {
      firsts.add(new StringScore(token,
          inst.getFemaleFreq(token) + inst.getMaleFreq(token)
      ));

      lasts.add(new StringScore(token,
          inst.getSurnameCount(token)));
    }

    String firstName = null;
    for (StringScore first : firsts) {
      if(first.getScore() > 0.0){
        firstName = first.getValue();
      }
    }

    String lastName = null;
    for (StringScore last : lasts) {
      if(last.getScore() == 0){
        break;
      }

      if(!last.getValue().equals(firstName)){
        lastName = last.getValue();
      }
    }

    return new DeducedName(firstName, null, lastName);
  }

  private static class StringScore implements Comparable<StringScore> {

    private final double score;
    private final String value;

    private StringScore(String value, double score) {
      this.score = score;
      this.value = value;
    }

    private double getScore() {
      return score;
    }

    private String getValue() {
      return value;
    }

    @Override
    public int compareTo(StringScore o) {
      return (int) (o.getScore()*1000l - getScore()*1000l);
    }

    @Override
    public String toString() {
      return "StringScore{" +
          "score=" + score +
          ", value='" + value + '\'' +
          '}';
    }
  }
}
