package ex0;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Класс сравнения производительности двух видов списков
 */
public final class ListTimeMeasurement {
    private final int MaxS;
    private final int Operations;


    /**
     * Конструктор по умолчанию
     */
    public ListTimeMeasurement() {
        MaxS = 2000;
        Operations = 1000;
    }

    /**
     * Конструктор с параметрами
     * @param MaxS максимальный размер списка
     * @param Operations количество операций
     */
    public ListTimeMeasurement(final int MaxS, final int Operations) {
        if (Operations > MaxS / 2) {
            throw new IllegalArgumentException("Invalid argument " + MaxS + " < " + Operations );
        }

        this.MaxS = MaxS;
        this.Operations = Operations;
    }

    /**
     * Метод, строящий таблицу по полученным данным
     */
    public void compare() {
        List<Integer> linkedList = new LinkedList<>();
        List<Integer> arrayList = new ArrayList<>();

        long linkedListFirstAddTime = measureOperation(linkedList, "FirstAdd");
        long arrayListFirstAddTime = measureOperation(arrayList, "FirstAdd");
        
        long linkedListMiddleAddTime = measureOperation(linkedList, "MiddleAdd");
        long arrayListMiddleAddTime = measureOperation(arrayList, "MiddleAdd");
        
        long linkedListLastAddTime = measureOperation(linkedList, "LastAdd");
        long arrayListLastAddTime = measureOperation(arrayList, "LastAdd");

        long linkedListBeginGetTime = measureOperation(linkedList, "BeginGet");
        long arrayListBeginGetTime = measureOperation(arrayList, "BeginGet");

        long linkedListMiddleGetTime = measureOperation(linkedList, "MiddleGet");
        long arrayListMiddleGetTime = measureOperation(arrayList, "MiddleGet");

        long linkedListEndGetTime = measureOperation(linkedList, "EndGet");
        long arrayListEndGetTime = measureOperation(arrayList, "EndGet");

        long linkedListFirstRemoveTime = measureOperation(linkedList, "FirstRemove");
        long arrayListFirstRemoveTime = measureOperation(arrayList, "FirstRemove");

        long linkedListMiddleRemoveTime = measureOperation(linkedList, "MiddleRemove");
        long arrayListMiddleRemoveTime = measureOperation(arrayList, "MiddleRemove");

        long linkedListLastRemoveTime = measureOperation(linkedList, "LastRemove");
        long arrayListLastRemoveTime = measureOperation(arrayList, "LastRemove");

        long linkedListRemoveIfTime = measureOperation(linkedList, "RemoveIf");
        long arrayListRemoveIfTime = measureOperation(arrayList, "RemoveIf");

        long linkedListContainsBeginTime = measureOperation(linkedList, "ContainsBegin");
        long arrayListContainsBeginTime = measureOperation(arrayList, "ContainsBegin");

        long linkedListContainsMiddleTime = measureOperation(linkedList, "ContainsMiddle");
        long arrayListContainsMiddleTime = measureOperation(arrayList, "ContainsMiddle");

        long linkedListContainsEndTime = measureOperation(linkedList, "ContainsEnd");
        long arrayListContainsEndTime = measureOperation(arrayList, "ContainsEnd");

        long linkedListSetBeginTime = measureOperation(linkedList, "SetBegin");
        long arrayListSetBeginTime = measureOperation(arrayList, "SetBegin");

        long linkedListSetMiddleTime = measureOperation(linkedList, "SetMiddle");
        long arrayListSetMiddleTime = measureOperation(arrayList, "SetMiddle");

        long linkedListSetEndTime = measureOperation(linkedList, "SetEnd");
        long arrayListSetEndTime = measureOperation(arrayList, "SetEnd");


        System.out.println("Operation \tLinkedList \tArrayList");
        System.out.println("    <Add>    _____________________");
        System.out.println("   FirstAdd  \t" + linkedListFirstAddTime + "\t\t\t"+ arrayListFirstAddTime);
        System.out.println("   MiddleAdd \t" + linkedListMiddleAddTime + "\t\t\t" + arrayListMiddleAddTime);
        System.out.println("   LastAdd   \t" + linkedListLastAddTime + "\t\t\t" + arrayListLastAddTime);

        System.out.println("    <Get>     ____________________");
        System.out.println("   BeginGet   \t" + linkedListBeginGetTime + "\t\t\t" + arrayListBeginGetTime);
        System.out.println("   MiddleGet  \t" + linkedListMiddleGetTime + "\t\t\t" + arrayListMiddleGetTime);
        System.out.println("   EndGet     \t" + linkedListEndGetTime + "\t\t\t" + arrayListEndGetTime);

        System.out.println("   <Remove>   ____________________");
        System.out.println(" FirstRemove   \t" + linkedListFirstRemoveTime + "\t\t\t" + arrayListFirstRemoveTime);
        System.out.println(" MiddleRemove  \t" + linkedListMiddleRemoveTime + "\t\t\t" + arrayListMiddleRemoveTime);
        System.out.println(" LastRemove    \t" + linkedListLastRemoveTime + "\t\t\t" + arrayListLastRemoveTime);
        System.out.println(" RemoveIf      \t" + linkedListRemoveIfTime + "\t\t\t" + arrayListRemoveIfTime);

        System.out.println("  <Contains>  ____________________");
        System.out.println(" ContainsBegin \t" + linkedListContainsBeginTime + "\t\t\t" + arrayListContainsBeginTime);
        System.out.println(" ContainsMiddle\t" + linkedListContainsMiddleTime + "\t\t\t" + arrayListContainsMiddleTime);
        System.out.println(" ContainsEnd   \t" + linkedListContainsEndTime + "\t\t\t" + arrayListContainsEndTime);

        System.out.println("    <Set>     ____________________");
        System.out.println("   SetBegin  \t" + linkedListSetBeginTime + "\t\t\t" + arrayListSetBeginTime);
        System.out.println("   SetMiddle \t" + linkedListSetMiddleTime + "\t\t\t" + arrayListSetMiddleTime);
        System.out.println("   SetEnd    \t" + linkedListSetEndTime + "\t\t\t" + arrayListSetEndTime);
    }

    /**
     * Метод, где проводятся замеры
     * @param list ссылка на список
     * @param method название метода
     * @return время в ms
     */
    private long measureOperation(List<Integer> list, final String method) {
        List<Integer> preparedList = prepareList(list, method);

        switch (method) {
            case "FirstAdd" -> {
                return TimeMeasurement.timing(() -> {
                    for (int i = 0; i < Operations; ++i) {
                        preparedList.addFirst(-1);
                    }
                });
            }
            case "MiddleAdd" -> {
                return TimeMeasurement.timing(() -> {
                    for (int i = 0; i < Operations; ++i) {
                        preparedList.add(preparedList.size() / 2, -1);
                    }
                });
            }
            case "LastAdd" -> {
                return TimeMeasurement.timing(() -> {
                    for (int i = 0; i < Operations; ++i) {
                        preparedList.addLast(-1);
                    }
                });
            }
            case "BeginGet" -> {
                return TimeMeasurement.timing(() -> {
                    for (int i = 0; i < Operations; ++i) {
                        preparedList.get(i);
                    }
                });
            }
            case "MiddleGet" -> {
                return TimeMeasurement.timing(() -> {
                    for (int i = 0; i < Operations; ++i) {
                        preparedList.get(preparedList.size() / 2 - i);
                    }
                });
            }
            case "EndGet" -> {
                return TimeMeasurement.timing(() -> {
                    for (int i = 0; i < Operations; ++i) {
                        preparedList.get(preparedList.size() - 1 - i);
                    }
                });
            }
            case "FirstRemove" -> {
                return TimeMeasurement.timing(() -> {
                    for (int i = 0; i < Operations; ++i) {
                        preparedList.removeFirst();
                    }
                });
            }
            case "MiddleRemove" -> {
                return TimeMeasurement.timing(() -> {
                    for (int i = 0; i < Operations; ++i) {
                        preparedList.remove(preparedList.size() / 2);
                    }
                });
            }
            case "LastRemove" -> {
                return TimeMeasurement.timing(() -> {
                    for (int i = 0; i < Operations; ++i) {
                        preparedList.removeLast();
                    }
                });
            }
            case "RemoveIf" -> {
                return TimeMeasurement.timing(() -> {
                    preparedList.removeIf(n -> (n % 2 == 0));
                });
            }
            case "ContainsBegin" -> {
                return TimeMeasurement.timing(() -> {
                    for (int i = 0; i < Operations; ++i) {
                        preparedList.contains(i);
                    }
                });
            }
            case "ContainsMiddle" -> {
                return TimeMeasurement.timing(() -> {
                    for (int i = 0; i < Operations; ++i) {
                        preparedList.contains(preparedList.size() / 2 - i);
                    }
                });
            }
            case "ContainsEnd" -> {
                return TimeMeasurement.timing(() -> {
                    for (int i = 0; i < Operations; ++i) {
                        preparedList.contains(preparedList.size() - 1 - i);
                    }
                });
            }
            case "SetBegin" -> {
                return TimeMeasurement.timing(() -> {
                    for (int i = 0; i < Operations; ++i) {
                        preparedList.set(i, -1);
                    }
                });
            }
            case "SetMiddle" -> {
                return TimeMeasurement.timing(() -> {
                    for (int i = 0; i < Operations; ++i) {
                        preparedList.set(preparedList.size() / 2 - i, -1);
                    }
                });
            }
            case "SetEnd" -> {
                return TimeMeasurement.timing(() -> {
                    for (int i = 0; i < Operations; ++i) {
                        preparedList.set(preparedList.size() - 1 - i, -1);
                    }
                });
            }
            default -> throw new RuntimeException("Unrealized method " + method);
        }
    }

    /**
     * Метод, который занимается подготовкой списка к тесту
     * @param list ссылка на список
     * @param method название метода
     * @return ссылка на созданный для замеров список
     */
    private List<Integer> prepareList(List<Integer> list, final String method) {
        if (list instanceof LinkedList<Integer>) {
            list = new LinkedList<>();
        } else {
            list = new ArrayList<>();
        }

        if (!method.contains("add")) {
            for (int i = 0; i < MaxS; ++i) {
                list.add(i);
            }
        }

        return list;
    }
}