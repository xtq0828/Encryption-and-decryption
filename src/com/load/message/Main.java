package com.load.message;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;

import com.barbeque.key.Barbequekey;

public class Main {

	/**
	 * @param args
	 * @throws IOException
	 */
	public boolean up(int flag, String filename) throws IOException {
		// TODO Auto-generated method stub
		Configuration conf = new Configuration();
		conf.set("mapred.job.tracker", "127.0.0.1:9001");
		conf.set("fs.default.name", "hdfs://127.0.0.1:9000");
		boolean result = false;

		HadoopFile hf = new HadoopFile(conf);

		Barbequekey temp = new Barbequekey();
		if (flag == 1) {
			result = hf.sendFile("hdfs://localhost:9000/",
					filename);
		} else if (flag == 2) {
			result = hf.downloadFile("hdfs://localhost:9000/"
					+ filename, "/home/barbeque/down/");
		}
		
		return result;
	}

}
