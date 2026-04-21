package com.abdullaevaziz.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Letter {

    private String topicLetter;
    private String textLetter;
    private List<String> userArrayList = new ArrayList<>();

    public Letter(String topicLetter, String textLetter, String users) {
        this.topicLetter = topicLetter;
        this.textLetter = textLetter;
        String[] strs = users.split("\n");
        this.userArrayList = new ArrayList<>(Arrays.asList(strs));
    }

    public String getTopicLetter() {
        return topicLetter;
    }

    public void setTopicLetter(String topicLetter) {
        this.topicLetter = topicLetter;
    }

    public String getTextLetter() {
        return textLetter;
    }

    public void setTextLetter(String textLetter) {
        this.textLetter = textLetter;
    }

    public List<String> getUserArrayList() {
        return userArrayList;
    }

    public void setUserArrayList(List<String> userArrayList) {
        this.userArrayList = userArrayList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Letter letter = (Letter) o;
        return Objects.equals(topicLetter, letter.topicLetter) && Objects.equals(textLetter, letter.textLetter) && Objects.equals(userArrayList, letter.userArrayList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topicLetter, textLetter, userArrayList);
    }

    @Override
    public String toString() {
        return "Letter{" +
                "topicLetter='" + topicLetter + '\'' +
                ", textLetter='" + textLetter + '\'' +
                ", userArrayList=" + userArrayList +
                '}';
    }
}
