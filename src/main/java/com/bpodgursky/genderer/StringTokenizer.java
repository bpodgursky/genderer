package com.bpodgursky.genderer;

import com.google.common.collect.Lists;

import java.util.List;

public class StringTokenizer {

  private static final String NON_LETTER= "[^a-zA-Z']";

  public static List<String> getTokens(String str){
    String[] tokens = str.split(NON_LETTER);

    List<String> nonEmpty = Lists.newArrayList();
    for (String token : tokens) {
      if(token.length() > 0){
        nonEmpty.add(token);
      }
    }

    return nonEmpty;
  }
}
