package com.jibaru.random.model;

public class RandomCongruentialMultiplicative implements Random {

    private final long seed;
    private long last;
    private long index = 0L;
    private long size = -1L;
    private final long a;
    private final long m;

    public RandomCongruentialMultiplicative(long seed, long a, long m) {
        this.seed = seed;
        this.last = seed;
        this.a = a;
        this.m = m;
    }

    public RandomCongruentialMultiplicative(long seed, long a, long m, long size) {
        this.seed = seed;
        this.last = seed;
        this.size = size;
        this.a = a;
        this.m = m;
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
        last = (a * last) % m;
        index++;
        return (double) last / m;
    }

    @Override
    public void reset() {
        index = 0;
        last = seed;
    }

    public static void main(String[] args) {
        Random random = new RandomCongruentialMultiplicative(12, 13, 100, 10);

        while (random.hasNext()) {
            System.out.println(random.getNext());
        }
    }

}
