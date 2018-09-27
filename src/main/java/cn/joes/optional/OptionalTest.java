package cn.joes.optional;

import cn.joes.User;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by myijoes on 2018/9/27.
 *
 * @author wanqiao
 */
public class OptionalTest {
    
    public static void main(String[] args) {
        create();
        method();
    }
    
    /**
     * 创建Optional对象
     * empty : 创建一个空的Optional对象
     * of : 不能为空
     * ofNullable : 方法接收一个T参数，如果T为null，它会调用empty方法，如果不为null则调用of方法
     *
     */
    public static void create() {
        Optional<Object> empty = Optional.empty();

        Optional<String> joes = Optional.of("joes");

        Optional<User> user = Optional.ofNullable(new User(1, "joes", 26));
    }

    /**
     * API详解 :
     *
     *  isPresent    : 如果这个对象的值不为null返回true,否则返回false。
     *  ifPresent    : 非NULL则执行代码里面的代码
     *  get          : 如果这个值存在，则返回这个值，如果这个值为null，则抛出异常。
     *  filter       : 符合判断式返回自己 否则为空
     *  map          : 非空的情况下对值进行处理返回新的Optional对象
     *  flatMap      : 与map不同的是function函数里面的返回值必须是Optional对象 , 而map不用
     *  orElse       : 为空的情况下返回其他的值
     *  orElseGet    : 和orElse相似,不同的是里面是方法不是对象
     *  orElseThrow  ; 为空的情况下抛出异常
     *
     */
    public static void method() {

        User joes = new User(1, "joes", 26);

        boolean isJoes = Optional.ofNullable(joes).isPresent();

        Optional.ofNullable(joes).ifPresent(s -> System.out.println(s.getUsername()));

        User joes1 = Optional.ofNullable(joes).get();

        Optional<User> joes2 = Optional.ofNullable(joes).filter(c -> c.getAge() > 20);

        Optional<ArrayList<User>> users = Optional.ofNullable(joes).map(c -> Lists.newArrayList(c));

        Optional.ofNullable(joes).map(c -> Lists.newArrayList(c)).orElse(Lists.newArrayList());

        Optional.ofNullable(joes).flatMap(c -> Optional.ofNullable(Lists.newArrayList(c)));

        User joes3 = Optional.ofNullable(joes).orElse(new User(2, "joes", 26));

        User user = Optional.ofNullable(joes).orElseGet(() -> getUser());

        Optional.ofNullable(joes).orElseThrow(Error::new);
    }

    public static User getUser() {
        return new User(2, "joes", 26);
    }
}
