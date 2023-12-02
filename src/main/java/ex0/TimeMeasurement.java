package ex0;

/**
 * Класс для измерения времени
 */
public final class TimeMeasurement {
    /**
     * Метод возвращаюций время, затраченное на выполнение операции
     * @param f лямбда функция
     * @return время в ms
     */
    public static long timing (Runnable f) {
        long beginTime = System.currentTimeMillis();

        f.run();

        return System.currentTimeMillis() - beginTime;
    }
}
