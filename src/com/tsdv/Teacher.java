package com.tsdv;

import java.util.ArrayList;
import java.util.List;

public class Teacher {
    int[] participationCourses; //participation courses of each teacher
    int numberPC; // number of participation course of each teacher
    List<Integer> teachCourse = new ArrayList<>();

    public int getNumberPC() {
        return numberPC;
    }

    public void setNumberPC(int numberPC) {
        this.numberPC = numberPC;
    }

    public int[] getParticipationCourses() {
        return participationCourses;
    }

    public void setParticipationCourses(int[] participationCourses) {
        this.participationCourses = participationCourses;
    }

    public List<Integer> getTeachCourse() {
        return teachCourse;
    }

    public void setTeachCourse(List<Integer> teachCourse) {
        this.teachCourse = teachCourse;
    }
}
