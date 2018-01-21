/**
 *
 *  @author Hravchenko Vladyslav S15567
 *
 */

package zad1;

import java.lang.reflect.*;
import java.math.*;
import java.util.*;

public class Calc {
    private TreeMap<String,Method> methods = new TreeMap<>();

    public String doCalc(String string) {
        try {
            String[] splitedArgs = string.split("\\s+");
            Class bigDecimal = Class.forName("java.math.BigDecimal");
            methods.put("+", bigDecimal.getMethod("add", BigDecimal.class, MathContext.class));
            methods.put("*", bigDecimal.getMethod("multiply", BigDecimal.class, MathContext.class));
            methods.put("-", bigDecimal.getMethod("subtract", BigDecimal.class, MathContext.class));
            methods.put("/", bigDecimal.getMethod("divide", BigDecimal.class, MathContext.class));
            return methods
                    .get(splitedArgs[1]).invoke(new BigDecimal(splitedArgs[0]), new BigDecimal(splitedArgs[2]), new MathContext(7))
                    .toString();
        } catch(Exception e) {
            return "Invalid calc";
        }
    }
}
