import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Q2MaxReducer extends Reducer<Object, Text, Text, IntWritable> {
    private int maxDownloads = Integer.MIN_VALUE;
    private final List<String> topCountries = new ArrayList<>();

    @Override
    public void reduce(Object key, Iterable<Text> values, Context context)
            throws IOException, InterruptedException {
        for (Text val : values) {
            String[] parts = val.toString().split("\\t");
            if (parts.length == 2) {
                String country = parts[0].trim();
                int count = Integer.parseInt(parts[1].trim());

                if (count > maxDownloads) {
                    maxDownloads = count;
                    topCountries.clear();
                    topCountries.add(country);
                } else if (count == maxDownloads) {
                    topCountries.add(country);
                }
            }
        }
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        for (String country : topCountries) {
            context.write(new Text(country), new IntWritable(maxDownloads));
        }
    }
}
