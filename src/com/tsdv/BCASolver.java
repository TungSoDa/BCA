package com.tsdv;

import java.io.*;

public class BCASolver {
    static String FILE_PATH = "E:\\TSDV Training\\DS&A\\BalancedCourseAssignment\\BalancedCourseAssignment.txt";
    BCAModel bcaModel = new BCAModel();

    public void loadData(String FILE_PATH) throws IOException {
        File file = new File(FILE_PATH);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
        String readFile;
        readFile = bufferedReader.readLine();
        String[] splitedString = readFile.split(" ");
        bcaModel.setNumberOfTeacher(Integer.parseInt(splitedString[0]));
        bcaModel.setNumberOfCourse(Integer.parseInt(splitedString[1]));

        bcaModel.teacherList = new Teacher[bcaModel.getNumberOfTeacher()];
        for (int i = 0; i < bcaModel.teacherList.length; i++) {
            readFile = bufferedReader.readLine();
            splitedString = readFile.split(" ");
            bcaModel.teacherList[i] = new Teacher();
            bcaModel.teacherList[i].setNumberPC(Integer.parseInt(splitedString[0]));
            bcaModel.teacherList[i].participationCourses = new int[bcaModel.teacherList[i].getNumberPC()];
            for (int j = 0 ; j < bcaModel.teacherList[i].getNumberPC(); j++) {
                bcaModel.teacherList[i].participationCourses[j] = Integer.parseInt(splitedString[j+1]);
            }
        }
        bcaModel.teach = new boolean[bcaModel.getNumberOfTeacher()][bcaModel.getNumberOfCourse()];
        for (int i = 0; i < bcaModel.getNumberOfTeacher() ; i++) {
            for (int j = 0; j < bcaModel.getNumberOfCourse(); j++) {
                if(bcaModel.numberContainInArr(bcaModel.teacherList[i].participationCourses, j+1)) {
                    bcaModel.teach[i][j] = true;
                }
            }
        }

        bcaModel.numberCC = Integer.parseInt(bufferedReader.readLine());
        bcaModel.listConflict = new Course[bcaModel.numberCC];
        for (int i = 0; i < bcaModel.listConflict.length; i++) {
            bcaModel.listConflict[i] = new Course();
            readFile = bufferedReader.readLine();
            splitedString = readFile.split(" ");
            bcaModel.listConflict[i].conflictCourse = new int[2];
            bcaModel.listConflict[i].conflictCourse[0] = Integer.parseInt(splitedString[0]);
            bcaModel.listConflict[i].conflictCourse[1] = Integer.parseInt(splitedString[1]);
        }
        // sort listConflict
        for (int i = 0; i < bcaModel.listConflict.length; i++) {
            for (int j = i+1; j < bcaModel.listConflict.length; j++) {
                if (bcaModel.listConflict[i].conflictCourse[0]>bcaModel.listConflict[j].conflictCourse[0]) {
                    Course temp = bcaModel.listConflict[i];
                    bcaModel.listConflict[i] = bcaModel.listConflict[j];
                    bcaModel.listConflict[j] = temp;
                }
                if (bcaModel.listConflict[i].conflictCourse[0]==bcaModel.listConflict[j].conflictCourse[0]) {
                    if (bcaModel.listConflict[i].conflictCourse[1]>bcaModel.listConflict[j].conflictCourse[1]) {
                        Course temp = bcaModel.listConflict[i];
                        bcaModel.listConflict[i] = bcaModel.listConflict[j];
                        bcaModel.listConflict[j] = temp;
                    }
                }
            }
        }
        bcaModel.conflict = new boolean[bcaModel.numberOfCourse][bcaModel.numberOfCourse];
        for (int i = 0; i < bcaModel.numberOfCourse; i++) {
            for (int j = 0; j < bcaModel.numberOfCourse; j++) {
                for (int k = 0; k < bcaModel.numberCC; k++) {
                    if(bcaModel.numberContainInArr(bcaModel.listConflict[k].conflictCourse, i+1, j+1)) {
                        bcaModel.conflict[i][j] = true;
                    }
//                    if(bcaModel.numberContainInArr(bcaModel.listConflict[k].conflictCourse, j+1, i+1)) {
//                        bcaModel.conflict[i][j] = true;
//                    }
                }
            }
        }
    }

    public void solve() {
//        // TODO: print teacherList
//        for (int i = 0; i < bcaModel.teacherList.length; i++) {
//            for (int j = 0 ; j < bcaModel.teacherList[i].getNumberPC(); j++) {
//                System.out.print(bcaModel.teacherList[i].participationCourses[j]+" ");
//            }
//            System.out.println();
//        }

//        // TODO: print listConflict
//        for (int i = 0; i < bcaModel.listConflict.length; i++) {
//            for (int j = 0; j < 2; j++) {
//                System.out.print(bcaModel.listConflict[i].conflictCourse[j] + " ");
//            }
//            System.out.println();
//        }

//        // TODO: print teach[i][j]
//        for (int i = 0; i < bcaModel.getNumberOfTeacher() ; i++) {
//            for (int j = 0; j < bcaModel.getNumberOfCourse(); j++) {
//                System.out.print(bcaModel.teach[i][j]+" ");
//            }
//            System.out.println();
//        }
//
//        // TODO: print conflict[i][j]
//        for (int i = 0; i < bcaModel.numberOfCourse; i++) {
//            for (int j = 0; j < bcaModel.numberOfCourse; j++) {
//                System.out.print(bcaModel.conflict[i][j]+" ");
//            }
//            System.out.println();
//        }
        bcaModel.printSolution();
    }

    public static void main(String[] args) throws IOException {
        BCASolver solver = new BCASolver();
        solver.loadData(FILE_PATH);
        solver.solve();
    }
}
