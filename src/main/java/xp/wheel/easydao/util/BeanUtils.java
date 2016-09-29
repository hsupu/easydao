package xp.wheel.easydao.util;

import java.lang.reflect.*;

/**
 * @author xp
 */
public class BeanUtils {

    public static boolean isGetter(Method method) {
        if (!method.getName().startsWith("get")) return false;
        if (method.getParameterCount() != 0) return false;
        if (void.class.equals(method.getReturnType())) return false;
        return true;
    }

    public static boolean isGetter(Method method, Field field) {
        if (!isGetter(method)) return false;
        return method.getReturnType().equals(field.getType());
    }

    public static boolean isSetter(Method method) {
        if (!method.getName().startsWith("set")) return false;
        if (method.getParameterCount() != 1) return false;
        return true;
    }

    public static boolean isSetter(Method method, Field field) {
        if (!isSetter(method)) return false;
        return method.getParameterTypes()[0].equals(field.getType());
    }

}
