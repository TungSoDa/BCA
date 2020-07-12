package com.tsdv;

import java.util.ArrayList;
import java.util.List;

public class BCAModel {
    int numberOfTeacher;
    int numberOfCourse;
    Teacher[] teacherList;
    int numberCC;
    Course[] listConflict;
    boolean teach[][];
    boolean conflict[][];
    List<Integer> load = new ArrayList<>();

    public int getNumberOfTeacher() {
        return numberOfTeacher;
    }

    public void setNumberOfTeacher(int numberOfTeacher) {
        this.numberOfTeacher = numberOfTeacher;
    }

    public int getNumberOfCourse() {
        return numberOfCourse;
    }

    public void setNumberOfCourse(int numberOfCourse) {
        this.numberOfCourse = numberOfCourse;
    }

    public List<Integer> checkConflict(int[] a) {
        List<Integer> b = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            for (int j = i+1; j < a.length; j++) {
                if (!conflict[a[i]][a[j]]) {
                    b.add(a[i]); b.add(a[j]);
                }
            }
        }
        return b;
    }
//    public List<Integer> checkConflict() {
//        List<Integer> b = new ArrayList<>();
//        for (int i = 0; i < numberOfCourse; i++) {
//            for (int j = 0; j < numberOfCourse; j++) {
//                for (int k = 0; k < numberOfTeacher; k++) {
//                    if(!conflict[i][j] && teach[k][i] && teach[k][j]) {
//                        b.add(i+1); b.add(j+1);
//                    }
//                }
//            }
//        }
//        return b;
//    }

    public void printSolution() {
        teacherList[0].teachCourse = checkConflict(teacherList[0].participationCourses);
        for (int i = 1; i < numberOfTeacher; i++) {
            for (int j = 0; j <= i-1; j++) {
                checkDuplicateCourse(teacherList[j].teachCourse, teacherList[i].teachCourse);
            }
            teacherList[i].teachCourse = checkConflict(teacherList[i].participationCourses);
        }
        for (int i = 0; i < numberOfTeacher; i++) {
            System.out.println("Courses assigned to teacher "+i+" : "+teacherList[i].teachCourse);
        }
        System.out.println();
        for (int i = 0; i < numberOfTeacher; i++) {
            load.add(teacherList[i].teachCourse.size());
        }
        System.out.println("Maximal load = "+max(load));
        System.out.println("Minimal load = "+min(load));
    }

    boolean numberContainInArr (int[] arr, int num){
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]==num) return true;
        }
        return false;
    }

    boolean numberContainInArr (int[] arr, int num1, int num2){
        if((num1 == num2) || (arr[0] == num1) && (arr[1] == num2)) return true;
        else return false;
    }
    // xoa nhung phan tu thuoc list B ma co trong list A
    void checkDuplicateCourse (List<Integer> a, List<Integer> b) {
        for (int i = 0; i < a.size(); i++) {
            for (int j = 0; j < b.size(); j++) {
                if (a.get(i)==a.get(j)) a.remove(a.get(i));
            }
        }
    }

    int max(List<Integer> a) {
        int max = a.get(0);
        for (int i = 1; i < a.size(); i++) {
            if (a.get(i) > max) max = a.get(i);
        }
        return max;
    }

    int min(List<Integer> a) {
        int min = a.get(0);
        for (int i = 1; i < a.size(); i++) {
            if (a.get(i) < min) min = a.get(i);
        }
        return min;
    }
}
