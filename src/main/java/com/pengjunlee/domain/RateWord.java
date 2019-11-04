package com.pengjunlee.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * 用于返回给前端使用的关键词封装类
 *
 * @author pengjunlee
 * @create 2019-09-03 14:44
 */
public class RateWord implements Comparable, Serializable {

    private static final long serialVersionUID = 1L;
    
    private String name;

    private int weight;


    public RateWord() {
        super();
    }

    public RateWord(String name) {
        this.name = name;
        this.weight = 1;
    }

    public void addWeight(int value) {
        this.weight += value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int hashCode() {
        return Objects.hashCode(this.name);
    }

    @Override
    public int compareTo(Object that) {
        if (this == that) {
            return 0;
        } else {
            int thatWeight = ((RateWord) that).getWeight();
            return thatWeight - this.getWeight();
        }
    }

    public boolean equals(Object that) {
        if (that == null) {
            return false;
        } else if (this.getClass() != that.getClass()) {
            return false;
        } else {
            RateWord thatWord = (RateWord) that;
            return Objects.equals(this.name, thatWord.name);
        }
    }

    public String toString() {
        return "{ name: " + this.name + ",weight: " + this.weight + "}";
    }
}
