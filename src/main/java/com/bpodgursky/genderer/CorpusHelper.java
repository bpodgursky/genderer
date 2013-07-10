package com.bpodgursky.genderer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;

public class CorpusHelper {
  public static Scanner openCorpus(String corpus) throws IOException {
    InputStream resourceAsStream = FreqCorpus.class.getClassLoader().getResourceAsStream(corpus);
    GZIPInputStream gzis = new GZIPInputStream(resourceAsStream);
    return new Scanner(gzis);
  }
}
