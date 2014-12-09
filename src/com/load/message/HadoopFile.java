package com.load.message;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import java.io.File;
import java.io.IOException;

public class HadoopFile {

	private Configuration conf = null;

	public HadoopFile() {
		conf = new Configuration();
		conf.addResource(new Path("/hadoop/etc/hadoop/core-site.xml"));
	}

	public HadoopFile(Configuration conf) {
		this.conf = conf;
	}

	public boolean sendFile(String path, String localfile) {
		File file = new File(localfile);
		if (!file.isFile()) {
			System.out.println("error:" + file.getName());

			return false;
		}
		try {
			FileSystem localFS = FileSystem.getLocal(conf);
			FileSystem hadoopFS = FileSystem.get(conf);
			Path hadPath = new Path(path);
			FSDataOutputStream fsOut = hadoopFS.create(new Path(path + "/"
					+ file.getName()));
			FSDataInputStream fsIn = localFS.open(new Path(localfile));
			byte[] buf = new byte[1024];
			int readbytes = 0;
			while ((readbytes = fsIn.read(buf)) > 0) {
				fsOut.write(buf, 0, readbytes);
			}
			fsIn.close();
			fsOut.close();
			FileStatus[] hadfiles = hadoopFS.listStatus(hadPath);
			for (FileStatus fs : hadfiles) {
				System.out.println(fs.toString());
			}
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean delFile(String hadfile) {
		try {
			FileSystem hadoopFS = FileSystem.get(conf);
			Path hadPath = new Path(hadfile);
			Path p = hadPath.getParent();
			boolean rtnval = hadoopFS.delete(hadPath, true);
			FileStatus[] hadfiles = hadoopFS.listStatus(p);
			for (FileStatus fs : hadfiles) {
				System.out.println(fs.toString());
			}
			return rtnval;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean downloadFile(String hadfile, String localPath) {
		try {
			FileSystem localFS = FileSystem.getLocal(conf);
			FileSystem hadoopFS = FileSystem.get(conf);
			Path hadPath = new Path(hadfile);
			FSDataOutputStream fsOut = localFS.create(new Path(localPath + "/"
					+ hadPath.getName()));
			FSDataInputStream fsIn = hadoopFS.open(hadPath);
			byte[] buf = new byte[1024];
			int readbytes = 0;
			while ((readbytes = fsIn.read(buf)) > 0) {
				fsOut.write(buf, 0, readbytes);
			}
			fsIn.close();
			fsOut.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
