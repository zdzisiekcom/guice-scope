package com.zdzisiek.guice.domain;

public class PackageImpl implements Package {

    private String from;

    private String to;

    private int weight;

    public PackageImpl(){

    }

    public PackageImpl(String from, String to, int weight) {

        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "PackageAssisted{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", weight=" + weight +
                '}';
    }
}
