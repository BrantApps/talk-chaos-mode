package com.brantapps.talks.meetup.fixture;

import org.apache.commons.lang3.RandomStringUtils;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Random;
import java.util.TimeZone;

/**
 * Random data generators for fixtures.
 */
public final class DataGenerator {
    private final Random random;

    public DataGenerator() {
        super();
        this.random = new Random();
    }


    /**
     * Generates a random string of a specified length.
     *
     * @param length the length of the string to generate.
     * @return a randomly generated string.
     */
    public String nextString(int length) {
        return RandomStringUtils.random(length, 32, 127, false, false, null, random);
    }


    /**
     * Return a random value from a set of valid values.
     *
     * @param <T>    Type of value to return.
     * @param values Possible values.
     * @return One of {@code values}.
     */
    public <T> T nextOneOf(T... values) {
        return values[nextInt(values.length)];
    }


    /**
     * Gets the next random integer in the range [0.. Integer.MAX_VALUE].
     *
     * @return a random integer.
     */
    public int nextInt() {
        return random.nextInt();
    }


    /**
     * Gets the next random integer in the range [0.. n-1].
     *
     * @param n the positive upper boundary for the result. Must be > 0.
     * @return an integer <b>less than</b> the upper boundary <code>n</code> and at least zero.
     */
    public int nextInt(int n) {
        return random.nextInt(n);
    }


    /**
     * Gets the next random integer in the range [m.. n-1].
     *
     * @param m the positive lower boundary for the result. Must be in the range [0.. n-1].
     * @param n the positive upper boundary for the result. Must be > 0.
     * @return an integer <b>less than</b> the upper boundary <code>n</code> and <b>higher or equal to</b> the lower boundary <code>m</code>.
     */
    public int nextInt(int m, int n) {
        return m + random.nextInt(n - m);
    }


    /**
     * Gets the next random long >= 0.
     *
     * @return a random long.
     */
    public long nextLong() {
        return Math.abs(random.nextLong());
    }


    /**
     * Gets the next random long in the range [0.. n-1].
     *
     * @param n the positive upper boundary for the result. Must be > 0.
     * @return a random long <b>less than</b> the upper boundary <code>n</code> and at least zero.
     */
    public long nextLong(long n) {
        return Math.abs(random.nextLong()) % n;
    }


    /**
     * Gets the next random double in the range [0..1) i.e. can never return 1.
     *
     * @return a random double.
     */
    public double nextDouble() {
        return Math.abs(random.nextDouble());
    }


    /**
     * Gets the next random float in the range [0..1) i.e. can never return 1.
     *
     * @return a random float.
     */
    public float nextFloat() {
        return Math.abs(random.nextFloat());
    }


    /**
     * Gets the next random boolean.
     *
     * @return a random boolean.
     */
    public boolean nextBoolean() {
        return random.nextBoolean();
    }

    public ZonedDateTime nextFutureTimeNotToday() {
        return ZonedDateTime.now(
                ZoneId.of(nextOneOf(ZoneId.getAvailableZoneIds().stream().toArray(String[]::new))))
                .plusDays(1)
                .plusWeeks(nextInt(0, 4));
    }
}
