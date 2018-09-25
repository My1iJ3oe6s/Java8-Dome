package cn.joes.listtomap;

import cn.joes.User;
import com.google.common.collect.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by myijoes on 2018/9/25.
 *
 * @author wanqiao
 */
public class ListToMapTest {

    public static void main(String[] args) {
        guavaMethod();
        java8Method();
        Multimaps();
    }

    /**
     * uniqueIndex(唯一索引): 通过指定key值创建Map
     *
     * 但是该方法存在两个问题:
     * 1. 数据来源的集合指定为key值的属性的值不能出现重复的情况
     * 2. key对应的value是唯一的,不是集合,不能器到分类的作用
     *
     */
    public static void guavaMethod() {
        List<User> users = Lists.newArrayList();
        users.add(new User(1, "Joe", 20));
        users.add(new User(2, "Tony", 23));
        users.add(new User(3, "Mary", 18));
        ImmutableMap<Integer, User> integerUserImmutableMap = FluentIterable.from(users).uniqueIndex(c -> c.getAge());
        ImmutableMap<Integer, User> integerUserImmutableMap1 = Maps.uniqueIndex(users, c -> c.getAge());
    }

    /**
     * 通过Java8来实现List转Map
     *
     * toMap可以指定key,value的值
     *
     */
    public static void java8Method() {
        List<User> users = Lists.newArrayList();
        users.add(new User(1, "Joe", 20));
        users.add(new User(2, "Tony", 23));
        users.add(new User(3, "Mary", 18));
        Map<Integer, User> collect = users.stream().collect(Collectors.toMap(c -> c.getAge(), p -> p));
    }

    /**
     * Multimaps.index():
     *
     * 创建的Map的key是可以重复的
     */
    public static void Multimaps() {
        List<User> users = Lists.newArrayList();
        users.add(new User(1, "Joe", 20));
        users.add(new User(2, "Tony", 23));
        users.add(new User(3, "Mary", 18));
        users.add(new User(4, "Henny", 18));
        ImmutableListMultimap<Integer, User> index = Multimaps.index(users, c -> c.getAge());
    }

    /**
     * groupingBy()分组
     *
     */
    public static void groupingBy() {
        List<User> users = Lists.newArrayList();
        users.add(new User(1, "Joe", 20));
        users.add(new User(2, "Tony", 23));
        users.add(new User(3, "Mary", 18));
        users.add(new User(4, "Henny", 18));
        HashMap<Integer, List<User>> collect =
                users.stream().collect(Collectors.groupingBy(c -> c.getAge(), HashMap::new, Collectors.toList()));

        Map<Integer, List<Integer>> collect1 = users.stream()
                .collect(Collectors.groupingBy(c -> c.getAge(), Collectors.mapping(p -> p.getId(), Collectors.toList())));
    }

    /**
     * groupingBy()分组
     *
     */
    public static void groupingBy1() {
        List<User> users = Lists.newArrayList();
        users.add(new User(1, "Joe", 20));
        users.add(new User(2, "Tony", 23));
        users.add(new User(3, "Mary", 18));
        users.add(new User(4, "Henny", 18));
        Map<Integer, List<Integer>> collect1 = users.stream()
                .collect(Collectors.groupingBy(c -> c.getAge(), Collectors.mapping(p -> p.getId(), Collectors.toList())));
    }

}
