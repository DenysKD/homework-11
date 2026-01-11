import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Task1 {
    static void main(String[] args) {
        List<String> names = new ArrayList<>(
                Arrays.asList("Peter", "Ivan", "Bogdan", "Viktoria", "Anna", "Veronika"));
        List<String> unsortedNumbers = List.of("1, 2, 0", "4, 5");

        List<String> filteredIndexNames = nameIndexFilter(names);
        List<String> sortedToUpperCaseNames = toUpperCseNames(names);

        System.out.println("names = " + filteredIndexNames);
        System.out.println("sortedToUpperCaseNames = " + sortedToUpperCaseNames);
        sortAndConcatNumbers(unsortedNumbers);

        List<Integer> numbers1 = List.of(1,2,3,4,5,6);
        List<Integer> numbers2 = List.of(7,6,8);

        System.out.println(zip(numbers1.stream(), numbers2.stream()).collect(Collectors.toList()));
    }

    //завдання 1
    public static List<String> nameIndexFilter(List<String> list) {

        return IntStream.range(0, list.size()).filter(i -> i % 2 != 0)
                                            .mapToObj(list::get).map(i -> String.valueOf(list.indexOf(i)) + ". " + i)
                                            .collect(Collectors.toList());
    }

    //завдання 2
    public static List<String> toUpperCseNames(List<String> list)
    {
        return list.stream().map(i -> i .toUpperCase()).sorted()
                            .collect(Collectors.toList());
    }

    //завдання 3
    public static void sortAndConcatNumbers(List<String> list)
    {
        String result = list.stream().map(s -> s.replaceAll("\\s+", ""))
                                     .flatMap(s -> Arrays.stream(s.split(",")))
                                     .sorted().collect(Collectors.joining(", "));
        System.out.println("result = " + result);
    }

    //завдання 4
    public static Stream<Long> numberGenerator(long seed, long a, long c, long m)
    {
        return Stream.iterate(seed, i -> (1 * (a * i + c) % m))/*.limit(10)*/;
    }

    //завдання 5
    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second)
    {
        Stream<T> newStream = Stream.empty();

        Iterator<T> firstStream = first.iterator();
        Iterator<T> secondStream = second.iterator();

        Iterator<T> addToStream = new Iterator<T>() {
            boolean turn = true;

            @Override
            public boolean hasNext() {
                return firstStream.hasNext() && secondStream.hasNext();
            }

            @Override
            public T next() {
                turn = !turn;
                return turn ? secondStream.next() : firstStream.next();
            }
        };

        Spliterator<T> spliter = Spliterators.spliteratorUnknownSize(addToStream, Spliterator.ORDERED);

        newStream = StreamSupport.stream(spliter, false);

        return newStream;
    }


}
