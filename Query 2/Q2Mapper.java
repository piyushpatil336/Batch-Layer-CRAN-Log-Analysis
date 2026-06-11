import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Q2Mapper extends Mapper<Object, Text, Text, IntWritable> {
    private static final IntWritable ONE = new IntWritable(1);
    private final Text country = new Text();

    @Override
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        // Skip empty lines
        String line = value.toString().trim();
        if (line.isEmpty()) return;

        String[] fields = line.split(",");
        // Ensure country index exists (fields[7])
        if (fields.length > 7) {
            String c = fields[7].trim();
            // Optional: skip header/blank/NA
            if (!c.isEmpty() && !c.equalsIgnoreCase("country") && !c.equalsIgnoreCase("NA")) {
                country.set(c);
                context.write(country, ONE);
            }
        }
    }
}
