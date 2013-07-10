package com.bpodgursky.genderer;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestEmailHelper {

  @Test
  public void testGetUsername(){
    assertEquals("bob", EmailHelper.getUsername("bob@gmail.com"));
    assertEquals("bob-joe", EmailHelper.getUsername("bob-joe@yahoo.com"));
    assertEquals("bob-joe0212", EmailHelper.getUsername("bob-JOe0212@yahoo.com"));
  }
}
