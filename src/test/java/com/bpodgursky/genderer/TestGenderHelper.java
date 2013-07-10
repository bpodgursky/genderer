package com.bpodgursky.genderer;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class TestGenderHelper {
  @Test
  public void testIt() throws IOException {
    assertEquals(Gender.MALE, GenderHelper.getGender(new DeducedName("bob", null, "smith")));
    assertEquals(Gender.FEMALE, GenderHelper.getGender(new DeducedName("anna", null, "smith")));
  }
}
