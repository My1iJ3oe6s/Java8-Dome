package cn.joes.collectors;

import cn.joes.User;
import com.google.common.collect.Lists;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 主要测试Collectors对象的API用法
 * <p>
 * Created by myijoes on 2018/9/19.
 *
 * @author wanqiao
 */
public class CollectorsTest {

    private static List<User> users;

    public static void main(String[] args) {
        users = Lists.newArrayList();
        users.add(new User(1, "Joe", 20));
        users.add(new User(2, "Tom", 12));
        users.add(new User(3, "Java", 24));
        users.add(new User(4, "Netty", 40));
        users.add(new User(5, "Guava", 30));
        users.add(new User(6, "Spring", 30));
        CollectorsTest collectorsTest = new CollectorsTest();
        collectorsTest.toMap(users);
        System.out.println(collectorsTest.counting(users));
        System.out.println(collectorsTest.reduce());
        List<Integer> mapping = collectorsTest.mapping(users);
    }

    public List<User> toList(List<User> users) {
        return users.stream().filter(c -> c.getAge() > 25L).collect(Collectors.toList());
    }

    public Set<User> toSet(List<User> users) {
        return users.stream().filter(c -> c.getAge() > 25L).collect(Collectors.toSet());
    }

    /**
     * toMap的四个参数分别为:
     * <p>
     * unction<? super T, ? extends K> keyMapper 键的来源
     * Function<? super T, ? extends U> valueMapper 值的来源
     * BinaryOperator<U> mergeFunction 合并函数 当键重复的情况下值的取值规则
     * Supplier<M> mapSupplier 收集函数
     *
     * @return
     */
    public LinkedHashMap<Integer, User> toMap(List<User> users) {

        LinkedHashMap<Integer, User> collect = users.stream().filter(c -> c.getAge() > 25)
                .collect(Collectors.toMap(c -> c.getAge(), (p) -> p,
                        (left, right) -> left.getId() > right.getId() ? left : right, LinkedHashMap::new));
        return collect;
    }

    public LinkedList<User> toCollection() {
        return users.stream().filter(c -> c.getAge() > 25L).collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * partitioningBy(分割) 和 groupingBy 的作用相识
     * 不同的是他的key值为true/false
     *
     * @return
     */
    public Map<Boolean, List<User>> partitioningBy(List<User> users) {
        Map<Boolean, Map<Integer, List<User>>> collect =
                users.stream().collect(Collectors.partitioningBy(c -> c.getAge() > 35, Collectors.groupingBy(c -> c.getAge())));

        return users.stream().collect(Collectors.partitioningBy(c -> c.getAge() > 35));
    }

    /**
     * partitioningBy(分割) 和 的作用相识
     * 不同的是他的key值为true/false
     *
     * @return
     */
    public Map<Integer, List<User>> groupingBy(List<User> users) {
        HashMap<Integer, List<User>> collect =
                users.stream().collect(Collectors.groupingBy(c -> c.getAge(), HashMap::new, Collectors.toList()));

        Map<Integer, List<Integer>> collect1 = users.stream()
                .collect(Collectors.groupingBy(c -> c.getAge(), Collectors.mapping(p -> p.getId(), Collectors.toList())));

        return users.stream().collect(Collectors.groupingBy(c -> c.getAge()));
    }

    public String joininng(List<User> users) {
        return Stream.of("1", "2", "3", "4").collect(Collectors.joining(",","[","]"));
    }

    /**
     * 统计数量
     *
     * @param users
     * @return
     */
    public Long counting(List<User> users) {
        return users.stream().collect(Collectors.counting());
    }

    /**
     * reduce
     *
     * ram users
     * @return
     */
    public int reduce() {
        return Stream.of(1, 2, 3, 4).reduce(100, (sum, item) -> sum + item);
    }

    /**
     * collectingAndThen 针对collerctor的对象做操作
     *
     * ram users
     * @return
     */
    public int collectingAndThen(List<User> users) {
        return users.stream().collect(Collectors.collectingAndThen(Collectors.groupingBy(c -> c.getAge()), Map::size));
    }

    /**
     * mapping():获取某个list对象属性的集合
     *
     */
    public List<Integer> mapping(List<User> users) {
        return users.stream()
                .collect(Collectors.mapping(p -> p.getAge(), Collectors.toList()));
    }

}
