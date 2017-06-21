/**
 * <h1>Task4</h1>
 * This class will list down all the files inside a directory and its sub-directories who have last modified timestamp 
 * between the start_ts and end_ts passed as an argument. The default value of start_ts is 0 and for end_ts is infinite.
 * */
package hdfs;

import java.io.*;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.FileStatus;

public class Task4 {
	public static void main(String[] args) {
		long start = 0;
		long end = Long.MAX_VALUE;
		
		if (args.length < 1 || args.length > 3) {
			System.out.println("Pass one, two or three arguments");
			System.exit(1);
		}
		else if (args.length == 2) {
			start = Long.parseLong(args[1]);
		}
		else if (args.length == 3) {
			start = Long.parseLong(args[1]);
			end = Long.parseLong(args[2]);
		}
		
		getStatus(new Path(args[0]), start, end);
	}
	
	public static void getStatus(Path path, Long start, Long end) {
		try
		{
			Configuration conf = new Configuration();
			FileSystem fileSystem = FileSystem.get(path.toUri(), conf);
			FileStatus[] fileStatus=fileSystem.listStatus(path);
			for (FileStatus fStat : fileStatus) {
				if (fStat.getModificationTime() >= start && fStat.getModificationTime() <= end) {
					getStatus(fStat.getPath(), start, end);
					System.out.println("All directories and files inside the given path based on given timestamp are: ");
				}
				else{
					System.out.println("There are no file for the given timestamp frame");
				}
			}

		}
		catch (IOException e)
		{
            e.printStackTrace();
		}
	}
}