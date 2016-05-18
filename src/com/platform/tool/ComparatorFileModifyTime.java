package com.platform.tool;

import java.io.File;
import java.util.Comparator;

/**
 * 
 * ClassName：ComparatorFileModifyTime
 * Description：文件最后修改时间比较
 * @author: 刘焕超
 * @date: 2015-5-18 下午12:09:38
 * note:
 *
 */
public class ComparatorFileModifyTime implements Comparator<File> {

	public int compare(File o1, File o2) {
		Long l = o2.lastModified();
		return l.compareTo(o1.lastModified());
	}

}
