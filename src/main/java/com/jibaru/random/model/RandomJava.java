package com.jibaru.random.model;

public class RandomJava implements Random {

    private final java.util.Random randomUtil;
    private long index = 0L;
    private long size = -1L;

    public RandomJava(long seed, long size) {
        this.size = size;
        this.randomUtil = new java.util.Random(seed);
    }

    public RandomJava(long seed) {
        this.randomUtil = new java.util.Random(seed);
    }

    @Override
    public boolean hasNext() {
        if (size != -1) {
            return (size - index > 0);
        }
        return true;
    }

    @Override
    public double getNext() {
        index++;
        return randomUtil.nextDouble();
    }

    @Override
    public void reset() {
        index = 0;
    }

    public static void main(String[] args) {
        Random random = new RandomJava(120, 10);

        while (random.hasNext()) {
            System.out.println(random.getNext());
        }
    }

}
