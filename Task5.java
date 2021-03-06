/**
 * <h1>Task5</h1>
 * This class will display the content of a file in HDFS on screen.
 * */
package hdfs;

import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class Task5 {
	public static void main(String[] args) throws Exception {
		if (args.length != 1) {
			System.out.println("Pass one argument");
			System.exit(1);
		}

		String uri = args[0];
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(URI.create(uri), conf);
		FSDataInputStream in = null;
		try {
			in = fs.open(new Path(uri));
			IOUtils.copyBytes(in, System.out, 4096, false);
		} 
		finally {
			IOUtils.closeStream(in);
		}
	}
}