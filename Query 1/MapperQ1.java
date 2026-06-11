import java.io.IOException;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Mapper;

public class MapperQ1 extends Mapper<Object, Text, Text, IntWritable>{

    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text("ggplot2");

    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {

        String line = value.toString();
        String[] fields = line.split(",");

        if(fields.length > 6 && 
        fields[6].replace("\"",
        "").trim().equals("ggplot2")){
            context.write(word, one);
        }
    }
}
