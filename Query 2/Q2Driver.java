import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Q2Driver {
    public static void main(String[] args) throws Exception {
        if (args.length < 3) {
            System.err.println("Usage: Q2Driver <input> <tempOutput> <finalOutput>");
            System.exit(2);
        }

        Configuration conf = new Configuration();

        // Job 1: country counts
        Job job1 = Job.getInstance(conf, "Country download counts");
        job1.setJarByClass(Q2Driver.class);

        job1.setMapperClass(Q2Mapper.class);
        job1.setCombinerClass(Q2CountryReducer.class);
        job1.setReducerClass(Q2CountryReducer.class);

        job1.setOutputKeyClass(Text.class);
        job1.setOutputValueClass(IntWritable.class);

        FileInputFormat.addInputPath(job1, new Path(args[0]));
        FileOutputFormat.setOutputPath(job1, new Path(args[1]));

        boolean ok = job1.waitForCompletion(true);
        if (!ok) System.exit(1);

        // Job 2: max country
        Job job2 = Job.getInstance(conf, "Max downloads by country");
        job2.setJarByClass(Q2Driver.class);

        // identity mapper (reads each line from job1 output)
        job2.setMapperClass(Mapper.class);
        job2.setInputFormatClass(TextInputFormat.class);

        // one reducer for global maximum
        job2.setNumReduceTasks(1);
        job2.setReducerClass(Q2MaxReducer.class);

        job2.setMapOutputKeyClass(LongWritable.class);
        job2.setMapOutputValueClass(Text.class);

        job2.setOutputKeyClass(Text.class);
        job2.setOutputValueClass(IntWritable.class);

        FileInputFormat.addInputPath(job2, new Path(args[1]));
        FileOutputFormat.setOutputPath(job2, new Path(args[2]));

        System.exit(job2.waitForCompletion(true) ? 0 : 1);
    }
}
