package com.bpodgursky.genderer;

import com.google.common.collect.Maps;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;

public class WordFreqCorpus {
  private static final String RESOURCE = "com/bpodgursky/genderer/SUBTLEXusrawdata.dat.gz";

  private final Map<String, Integer> freqs = Maps.newHashMap();

  private static WordFreqCorpus inst;

  public static WordFreqCorpus inst() throws IOException {
    if(inst == null){
      inst = new WordFreqCorpus();
    }
    return inst;
  }

  private WordFreqCorpus() throws IOException {

    InputStream resourceAsStream = WordFreqCorpus.class.getClassLoader().getResourceAsStream(RESOURCE);
    GZIPInputStream gzis = new GZIPInputStream(resourceAsStream);

    Scanner scan = new Scanner(gzis);
    scan.nextLine();

    while(scan.hasNext()){
      String x = scan.nextLine();
      String[] parts = x.split("\\s+");

      String clean = parts[0].trim().toLowerCase();
      Integer count = Integer.parseInt(parts[1]);

      if(!freqs.containsKey(clean)){
        freqs.put(clean, 0);
      }

      freqs.put(clean, freqs.get(clean)+count);
    }
  }

  public Integer getVal(String val){
    return freqs.get(val.toLowerCase().trim());
  }
}
