package psyco.web;

import com.google.common.collect.Lists;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Modifier;

/**
 * Created by lipeng on 15/6/19.
 */
public class ThymleafFunctionRegister {

    public static void regist(Class<?> clz) {
        StandardEvaluationContext  standardEvaluationContext = new StandardEvaluationContext();
        Lists.newArrayList(clz.getDeclaredMethods()).stream().filter(m -> Modifier.isPublic(m.getModifiers()) && Modifier.isStatic(m.getModifiers())).forEach(e -> {
            System.out.println(e.getName());
            standardEvaluationContext.registerFunction(e.getName(),e);
        });
    }
}