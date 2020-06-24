package com.excercise.code.wordexmp;

import java.util.ArrayList;
import java.util.List;

public class Word {

  public static void main(String[] args) {
    String testStr = "Ankush Here is the test for today";
    formatAndPrint(changeStrToList(testStr), 10);
  }

  private static List<String> changeStrToList(String testStr) {
    List<String> wordList = new ArrayList<>();
    StringBuilder str = new StringBuilder();
    for (int i =0; i<testStr.length();i++) {
      if (testStr.charAt(i) != ' ') {
        str.append(testStr.charAt(i));
      } else {
        wordList.add(str.toString());
        str = new StringBuilder();
      }
      if (i==testStr.length()) {
        wordList.add(str.toString());
      }
    }
    return wordList;
  }

  private static void formatAndPrint(List<String> strArray, int lineLimit) {
    StringBuilder str = new StringBuilder();
    StringBuilder temp = new StringBuilder();
    if (strArray.toString().length()<lineLimit) {
      System.out.print(strArray);
    } else {
      for (int i =0; i<strArray.size();i++) {
        if (str.length()<lineLimit &&  temp.append(strArray.get(i)).append(" ").length()<lineLimit) {
          str = str.append(strArray.get(i)).append(" ");
          temp= str;
        } else {
          str = str.append("\n").append(" ").append(strArray.get(i));
          lineLimit = lineLimit+lineLimit;
        }
      }
    }
    System.out.print(str);

  }
}
