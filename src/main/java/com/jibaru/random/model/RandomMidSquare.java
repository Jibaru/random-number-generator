package com.jibaru.random.model;

public class RandomMidSquare implements Random {

    private long seed;
    private long last;
    private long index = 0L;
    private long size = -1L;
    private int seedSize;
    private long denominator;

    public RandomMidSquare(long seed) {
        this.seed = seed;
        this.last = seed;
        this.seedSize = Long.toString(seed).length();
        this.denominator = Long.parseLong('1' + new String(new char[seedSize]).replace('\0', '0'));
    }

    public RandomMidSquare(long seed, long size) {
        this.seed = seed;
        this.last = seed;
        this.size = size;
        this.seedSize = Long.toString(seed).length();
        this.denominator = Long.parseLong('1' + new String(new char[seedSize]).replace('\0', '0'));
    }

    @Override
    public boolean hasNext() {
        if (size != -1) {
            return (size - index > 0);
        }
        return true;
    }

    private long getMiddle(long n) {
        String nstring = Long.toString(n);

        if (nstring.length() < seedSize * 2) {
            nstring = new String(new char[seedSize * 2 - nstring.length()]).replace('\0', '0') + nstring;
        }

        int paddedSeedSize = (seedSize % 2 == 0 ? seedSize : seedSize + 1);

        String middle = nstring;

        for (int i = 1; i <= paddedSeedSize; i += 2) {
            middle = middle.substring(1);
            middle = middle.substring(0, middle.length() - 1);
        }

        return Long.parseLong(middle);
    }

    @Override
    public double getNext() {
        long power = (long) Math.pow(last, 2);
        last = getMiddle(power);
        index++;
        return (double) last / denominator;
    }

    @Override
    public void reset() {
        index = 0;
        last = seed;
    }

    public static void main(String[] args) {
        Random random = new RandomMidSquare(5497, 10);

        while (random.hasNext()) {
            System.out.println(random.getNext());
        }
    }
}
