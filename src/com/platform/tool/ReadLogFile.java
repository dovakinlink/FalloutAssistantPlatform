package com.platform.tool;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 
 * ClassName：ReadLogFile
 * Description：读取日志文件工具类
 * @author: 刘焕超
 * @date: 2015-5-19 下午4:54:56
 * note:
 *
 */
public class ReadLogFile {
	/**
	 * 
	 * @param filename
	 *            目标文件
	 * @param charset
	 *            目标文件的编码格式
	 * @throws Exception
	 */
	public static String read(String filename, String charset, Long linenum) throws Exception {

		StringBuilder sb = new StringBuilder();
		RandomAccessFile rf = null;

		try {
			rf = new RandomAccessFile(filename, "r");
			long len = rf.length();
			long start = rf.getFilePointer();
			long nextend = start + len - 1;
			String line;
			rf.seek(nextend);
			int c = -1;
			long count = 0;

			while (nextend > start) {
				c = rf.read();
				if (c == '\n' || c == '\r') {
					line = rf.readLine();
					if (line != null) {
						sb.append(new String(line.getBytes("ISO-8859-1"), charset) + "\n");
						count++;
						if (count == linenum) {
							break;
						}
					}
					nextend--;
				}
				nextend--;
				rf.seek(nextend);

				if (nextend == 0) {// 当文件指针退至文件开始处，输出第一行  
					sb.append(new String(rf.readLine().getBytes("ISO-8859-1"), charset));
				}
			}
		} catch (FileNotFoundException e) {
			throw new Exception("没有找到日志文件:" + filename + e.getMessage());
		} catch (IOException e) {
			throw new Exception("文件读取失败:" + e.getMessage());
		} finally {
			try {
				if (rf != null)
					rf.close();
			} catch (IOException e) {
			}
		}

		return sb.toString();
	}

}
