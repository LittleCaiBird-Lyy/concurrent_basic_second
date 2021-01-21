package cn.second.basic.p10;

/**
 * 使用AtomicIntegerFieldUpdater更新的字段必须使用volatile修饰
 *
 */
public class User {
    int id;
    volatile int age;

    public User(int id, int age) {
        this.id = id;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                '}';
    }
}
