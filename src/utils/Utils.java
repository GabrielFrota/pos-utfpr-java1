package utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.LinkedList;

public class Utils {
    private static int tabcnt = 0;

    public static String prettyPrint(Object o) {
        var ll = new LinkedList<Class<?>>();
        for (Class<?> c = o.getClass(); !c.equals(Object.class); c = c.getSuperclass()) {
            ll.addFirst(c);
        }
        var sb = new StringBuilder();
        var df = new DecimalFormat("0.000");
        tabcnt++;
        sb.append("{\n").append("\t".repeat(tabcnt)).append("CLASS_NAME: ")
                .append(o.getClass().getName()).append('\n');
        for (var c : ll) {
            var fields = c.getDeclaredFields();
            for (var f : fields) {
                f.setAccessible(true);
                try {
                    var name = f.getName();
                    String valAsString;
                    if (!name.equals("velocMax")) {
                        valAsString = f.get(o).toString();
                        appendNameValue(sb, name, valAsString);
                    } else {
                        valAsString = df.format(BigDecimal.valueOf((Float)f.get(o)));
                        appendNameValue(sb, name, valAsString);
                        name = "calcVel(velocMax)";
                        var val = c.getMethod("calcVel", float.class)
                                .invoke(o, f.get(o));
                        valAsString = df.format(BigDecimal.valueOf((Float)val));
                        appendNameValue(sb, name, valAsString);
                    }
                } catch (Exception ignored) {}
            }
            try {
                var val = c.getMethod("calcular").invoke(o);
                var name = "calcular()";
                appendNameValue(sb, name, val.toString());
            } catch (Exception ignored) {}
        }
        tabcnt--;
        sb.append("\t".repeat(tabcnt)).append('}');
        return sb.toString();
    }

    private static void appendNameValue(StringBuilder sb, String name, String value) {
        sb.append("\t".repeat(tabcnt)).append(name)
                .append(": ").append(value)
                .append('\n');
    }
}