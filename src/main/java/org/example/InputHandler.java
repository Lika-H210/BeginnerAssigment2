package org.example;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class InputHandler {

  //存在するCommandが入力されている場合のみCommandのNumberを渡す
  public int inputCommand(Scanner scanner) {
    int inputNumber;
    while (true) {
      //int型の入力値を受け取る
      inputNumber = inputInt(scanner, "1～6の数値を入力してください。");

      //commandとの整合性検証
      if (inputNumber >= 1 && inputNumber <= 6) {
        return inputNumber;
      } else {
        System.out.println("入力値が不正です。: 「1～6の整数」を入力してください。");
      }
    }
  }

  //フルネームを返す。(ミドルネームは未考慮）
  public String inputName(Scanner scanner) {

    System.out.println("登録する学生の名前");
    System.out.print("姓を入力してください。 > ");
    String lastName = scanner.next();

    System.out.print("名を入力してください。 > ");
    String firstName = scanner.next();

    return lastName + " " + firstName;
  }

  //入力したスコア(double型)を返す
  public double inputScore(Scanner scanner) {
    while (true) {
      System.out.print("点数を入力してください。 > ");

      if (scanner.hasNextDouble()) {
        return scanner.nextDouble();
      } else {
        System.out.println("入力値が不正です。「数値」を入力してください。");
        scanner.next();
      }
    }
  }

  //使用可能なIDを返す(※使用可能＝引数:studentIdListに含まれている)
  public Optional<Integer> inputId(Scanner scanner, List<Integer> studnetIdList) {
    //int型の入力値を受け取る
    int inputNumber = inputInt(scanner, "対象の学生のIDを入力してください。");
    //登録されているIDか否か検証
    if (studnetIdList.contains(inputNumber)) {
      return Optional.of(inputNumber);
    } else {
      System.out.println("入力されたIDは存在しません。IDを確認してください。");
      return Optional.empty();
    }
  }

  //整数か否かの確認
  private int inputInt(Scanner scanner, String inputMessage) {
    while (true) {
      System.out.print(inputMessage + " > ");

      if (scanner.hasNextInt()) {
        return scanner.nextInt();
      } else {
        System.out.println("入力値が不正です。「整数」を入力してください。");
        scanner.next();
      }
    }
  }
}
