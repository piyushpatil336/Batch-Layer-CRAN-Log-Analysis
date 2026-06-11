import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Q4Mapper extends Mapper<Object, Text, Text, IntWritable> {
    private static final IntWritable ONE = new IntWritable(1);
    private final Text outKey = new Text();

    private static String clean(String s) {
        if (s == null) return "";
        return s.trim().replace("\"", "");
    }

    private static boolean isIreland(String c) {
        c = clean(c).toLowerCase();
        return c.equals("ie") || c.equals("ireland");
    }

    @Override
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        if (line == null || line.trim().isEmpty()) return;

        String[] f = line.split(",", -1);   // keep empty columns
        if (f.length < 9) return;           // need enough columns

        // Try likely positions
        String pkg0 = clean(f[0]); // common package field
        String pkg6 = clean(f[6]); // your previous package field
        String c7   = clean(f[7]); // your previous country field
        String c8   = clean(f[8]); // common country_code field in CRAN logs

        String pkg = !pkg0.isEmpty() ? pkg0 : pkg6;

        if (!pkg.isEmpty() && (isIreland(c7) || isIreland(c8))) {
            outKey.set(pkg);
            context.write(outKey, ONE);
        }
    }
}

