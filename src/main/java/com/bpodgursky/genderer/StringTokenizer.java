package com.bpodgursky.genderer;

import com.google.common.collect.Lists;

import java.io.IOException;
import java.util.List;

public class StringTokenizer {

  private static final String NON_LETTER= "[^a-zA-Z']";

  public static List<String> getTokens(String str) throws IOException {
    String[] tokens = str.split(NON_LETTER);

    List<String> nonEmpty = Lists.newArrayList();
    for (String token : tokens) {
      if(token.length() > 0){
        nonEmpty.addAll(getBestParts(token));
      }
    }

    return nonEmpty;
  }

  public static List<String> getBestParts(String token) throws IOException {

    int bestStart = -1;
    int bestEnd = -1;
    double bestScore = -1;

    for(int i = 1; i <= token.length(); i++){
      for(int j = 0; j <= token.length() - i; j++){
        String substr = token.substring(j, j + i);
        double score = score(substr);
        if(score > bestScore){
          bestScore = score;
          bestStart = j;
          bestEnd = j+i;
        }
      }
    }


    List<String> toReturn = Lists.newArrayList();
    if(bestStart > 0){
      toReturn.addAll(getBestParts(token.substring(0, bestStart)));
    }
    toReturn.add(token.substring(bestStart, bestEnd));
    if(bestEnd < token.length()){
      toReturn.addAll(getBestParts(token.substring(bestEnd, token.length())));
    }
    return toReturn;
  }

  private static Double score(String substr) throws IOException {
    FreqCorpus inst = FreqCorpus.inst();
    Double val = inst.getFreq(substr);

    return Math.pow(substr.length(), 4) * val;
  }
}
