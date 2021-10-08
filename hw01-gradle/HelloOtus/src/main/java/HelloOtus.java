import com.google.common.collect.Lists;

import java.util.List;

public class HelloOtus {
    public static void main(String... args) {
        List<String> teachers = Lists.newArrayList("Alexander Orudzhev",
                "Vitaly Kutsenko",
                "Vyacheslav Lapin",
                "Sergey Petrelevich",
                "Strekalov Pavel");

        System.out.println("Hello teachers: " + Lists.reverse(teachers));
    }


}
