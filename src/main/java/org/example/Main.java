package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    InputHandler input = new InputHandler();
    List<Student> studentList = new ArrayList<>();
    CommandActions commandAction = new CommandActions(studentList, input);

    while (true) {
      System.out.println();
      System.out.println("===== メニュー =====");
      System.out.println("1. 学生を追加");
      System.out.println("2. 学生を削除");
      System.out.println("3. 点数を更新");
      System.out.println("4. 平均点を計算");
      System.out.println("5. 全学生の情報を表示");
      System.out.println("6. 終了");
      System.out.println("====================");

      int commandNumber = input.inputCommand(scanner);

      if (commandNumber == 6) {
        System.out.println("終了します。");
        break;
      }

      switch (commandNumber) {
        case 1 -> {
          commandAction.addStudent(scanner);
          break;
        }
        case 2 -> {
          commandAction.removeStudent(scanner);
          break;
        }
        case 3 -> {
          commandAction.updateStudent(scanner);
          break;
        }
        case 4 -> {
          commandAction.getScoreAverage();
          break;
        }
        case 5 -> {
          commandAction.getAllStudents();
          break;
        }
      }
    }
  }
}