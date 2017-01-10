package domain;

import json.*;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {
    private final String name;
    private final String surname;
    private final Integer year;
    private final Tuple<String, Integer>[] exams;

    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        // ToDo
        this.name = name;
        this.surname = surname;
        this.year = year;
        this.exams = exams;
        // ToDo
    }

    @Override
    public JsonObject toJsonObject() {
        // ToDo
        return null;
    }
}