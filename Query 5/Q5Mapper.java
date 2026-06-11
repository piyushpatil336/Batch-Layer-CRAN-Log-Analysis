import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Q5Mapper extends Mapper<Object, Text, Text, IntWritable> {
    private static final IntWritable ONE = new IntWritable(1);
    private final Text osKey = new Text();

    @Override
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString().trim();
        if (line.isEmpty()) return;

        String[] fields = line.split(",", -1); // keep empty columns
        if (fields.length > 5) {
            String os = fields[5].trim().replace("\"", "");

            // skip header/empty/null-like values
            if (!os.isEmpty()
                    && !os.equalsIgnoreCase("r_os")
                    && !os.equalsIgnoreCase("na")
                    && !os.equalsIgnoreCase("null")) {
                osKey.set(os);
                context.write(osKey, ONE);
            }
        }
    }
}
