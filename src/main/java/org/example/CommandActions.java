package org.example;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Scanner;

public class CommandActions {

  private List<Student> studentList;
  private InputHandler input;

  public CommandActions(List<Student> studentList, InputHandler input) {
    this.studentList = studentList;
    this.input = input;
  }

  //生徒情報の追加
  public void addStudent(Scanner scanner) {
    String name = input.inputName(scanner);
    double score = input.inputScore(scanner);

    int id = studentList.stream()
        .mapToInt(Student::getId)
        .max()
        .orElse(0) + 1;

    Student student = new Student(id, name, score);

    studentList.add(student);
  }

  public void removeStudent(Scanner scanner) {
    List<Integer> idList = getIdList();
    Optional<Integer> targetId = input.inputId(scanner, idList);

    targetId.ifPresentOrElse(
        id -> {
          Optional<Student> removedStudent = studentList.stream()
              .filter(student -> Objects.equals(student.getId(), id))
              .findFirst();
          if (removedStudent.isPresent()) {
            studentList.remove(removedStudent.get());
            System.out.println(removedStudent.get().getName() + "さんの情報を削除しました。");
          }
        },
        () -> System.out.println("削除処理を終了します。")
    );
  }

  public void updateStudent(Scanner scanner) {
    List<Integer> idList = getIdList();
    Optional<Integer> targetId = input.inputId(scanner, idList);

    targetId.ifPresentOrElse(
        id -> {
          double updateScore = input.inputScore(scanner);
          studentList.stream()
              .filter(student -> Objects.equals(student.getId(), id))
              .findFirst()
              .ifPresent(student -> student.setScore(updateScore));
        },
        () -> System.out.println("更新処理を終了します。")
    );
  }

  //平均点を表示
  public void getScoreAverage() {
    OptionalDouble average = studentList.stream().mapToDouble(Student::getScore).average();

    if (average.isPresent()) {
      System.out.println("平均点：" + average.getAsDouble());
    } else {
      System.out.println("計算不可：生徒情報が1件も登録されていないため計算できません。");
      System.out.println("生徒情報を登録の上、再実行してください。");
      System.out.println("平均点計算処理を終了します。");
    }
  }

  //全生徒情報の表示
  public void getAllStudents() {
    if (studentList.isEmpty()) {
      System.out.println("登録されている学生情報はありません。");
    } else {
      studentList.forEach(System.out::println);
    }
  }

  //Listに登録されているIdのListを作成
  private List<Integer> getIdList() {
    return studentList.stream().
        map(Student::getId)
        .toList();
  }
}
