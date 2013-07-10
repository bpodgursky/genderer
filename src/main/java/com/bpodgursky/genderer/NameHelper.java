package com.bpodgursky.genderer;

import com.google.common.collect.Lists;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class NameHelper {

  public static DeducedName getName(List<String> tokens) throws IOException {
    FreqCorpus inst = FreqCorpus.inst();

    List<StringScore> firsts = Lists.newArrayList();
    List<StringScore> lasts = Lists.newArrayList();

    for (String token : tokens) {
      firsts.add(new StringScore(token,
          inst.getFemaleFreq(token) + inst.getMaleFreq(token)));

      lasts.add(new StringScore(token,
          inst.getSurnameFreq(token)));

    }

    Collections.sort(firsts);
    Collections.sort(lasts);

    String firstName = null;
    for (StringScore first : firsts) {
      if(first.getScore() > 0.0){
        firstName = first.getValue();
        break;
      }
    }


    String lastName = null;
    for (StringScore last : lasts) {
      if(last.getScore() == 0){
        break;
      }

      if(!last.getValue().equals(firstName)){
        lastName = last.getValue();
        break;
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

      if(getScore() > o.getScore()){
        return -1;
      }else if(o.getScore() > getScore()){
        return 1;
      }

      return 0;
    }

    @Override
    public String toString() {
      return "StringScore{" +
          "score=" + score +
          ", value='" + value + '\'' +
          '}';
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof StringScore)) return false;

      StringScore that = (StringScore) o;

      if (Double.compare(that.score, score) != 0) return false;
      if (value != null ? !value.equals(that.value) : that.value != null) return false;

      return true;
    }

    @Override
    public int hashCode() {
      int result;
      long temp;
      temp = Double.doubleToLongBits(score);
      result = (int) (temp ^ (temp >>> 32));
      result = 31 * result + (value != null ? value.hashCode() : 0);
      return result;
    }
  }
}
