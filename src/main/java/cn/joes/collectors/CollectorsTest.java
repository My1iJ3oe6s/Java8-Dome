package cn.joes.collectors;

import cn.joes.User;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

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
        collectorsTest.toMap();
    }

    public List<User> toList() {
        return users.stream().filter(c -> c.getAge() > 25L).collect(Collectors.toList());
    }

    public Set<User> toSet() {
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
    public LinkedHashMap<Integer, User> toMap() {

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
    public Map<Boolean, List<User>> partitioningBy() {
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
    public Map<Integer, List<User>> groupingBy() {
        HashMap<Integer, List<User>> collect =
                users.stream().collect(Collectors.groupingBy(c -> c.getAge(), HashMap::new, Collectors.toList()));

        return users.stream().collect(Collectors.groupingBy(c -> c.getAge()));
    }

    /*public Map<Integer, List<User>> groupingBy() {
       // users.stream().
       // users.stream().collect(Collectors.joining(",","[","]"));

        return users.stream().collect(Collectors.groupingBy(c -> c.getAge()));
    }*/




}
