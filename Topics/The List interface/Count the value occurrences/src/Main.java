import java.util.List;

class Counter {

    public static boolean checkTheSameNumberOfTimes(int elem, List<Integer> list1, List<Integer> list2) {

        int count1 = (int) list1.stream().filter(integer -> integer == elem).count();
        int count2 = (int) list2.stream().filter(integer -> integer == elem).count();
        return count1 == count2;
    }
}