package com.bpodgursky.genderer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailHelper {

  private static final Pattern EMAIL_PATTERN = Pattern.compile(
      "([a-z0-9._%+-]+)@([a-z0-9.-]+\\.[a-z]{2,4})"
  );

  public static String getUsername(String email){
    String downcase = email.toLowerCase();
    Matcher matcher = EMAIL_PATTERN.matcher(downcase);

    if(matcher.matches()){
      return matcher.group(1);
    }

    return null;
  }

}
