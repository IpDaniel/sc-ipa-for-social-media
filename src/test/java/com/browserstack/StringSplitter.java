package com.browserstack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StringSplitter {

  public static List<String> splitStringRandomly(String input) {
    List<String> result = new ArrayList<>();
    Random random = new Random();
    int length = input.length();
    int i = 0;

    while (i < length) {
      // Decide whether to take 1 or 2 characters
      int take = random.nextBoolean() ? 1 : 2;
      // Adjust if we are at the end and can't take 2 characters
      if (i + take > length) {
        take = 1;
      }
      result.add(input.substring(i, i + take));
      i += take;
    }

    return result;
  }
}

