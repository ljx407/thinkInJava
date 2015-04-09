package shsf;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class SysoToPrintln {

	@Test
	public void test() {
		String fileRootPath = "G:\\workspaceSTS\\thinkingInJava\\test\\holding\\";
		List<File> fileList = getFileInRootDerectory(fileRootPath);
		for (File file : fileList) {
//			println(file.getAbsolutePath());
			if("TestChange.java".equals(file.getName())) {
				addPrintUtil(file.getAbsolutePath());
			}
		}

	}
	
	@Test
	public void test1() {
		String s = "<p>\n\t&nbsp; &nbsp; 我们的家\n</p>\n<p>\n\t&nbsp; &nbsp; hello\n</p>\n";
		s = s.replaceAll(Pattern.quote("[\t\n\r]"), "");
		System.out.println(s);
		
	}
	
	public List<File> getFileInRootDerectory(String fileRootPath) {
		List<File> fileList = new LinkedList<File>();
		File rootDirectory = new File(fileRootPath);
		File[] files = null;
		if (rootDirectory.isDirectory()) {
			files = rootDirectory.listFiles();
		}
		for (File file : files) {
			if (file.isFile()) {
				fileList.add(file);
			} else {
				fileList.addAll(getFileInRootDerectory(file.getAbsolutePath()));
			}
		}
		return fileList;
	}

	private void addPrintUtil(String filePath) {
		BufferedReader bufferedReader = null;
		BufferedWriter bufferedWriter = null;
		try {
			FileReader fileReader = new FileReader(filePath);
			bufferedReader = new BufferedReader(fileReader);

			String line = null;
			StringBuffer stringBuffer = new StringBuffer();
			String addStr = "import static util.PrintUtil.*;";
			while ((line = bufferedReader.readLine()) != null) {
				if(line.indexOf("//") != -1) {
					line = line.replaceAll(Pattern.quote("//"), Matcher.quoteReplacement("/* ")) + " */";
				}
				stringBuffer.append(line);
				
			}
			String textStr = stringBuffer.toString();

			if (textStr.indexOf("System.out.println") != -1 || textStr.indexOf("System.out.print") != -1) {
				textStr = textStr.replaceFirst(Pattern.quote("public class"), Matcher.quoteReplacement(addStr + " public class"))
							     .replaceAll(Pattern.quote("System.out.println"), Matcher.quoteReplacement("println"))
							     .replaceAll(Pattern.quote("System.out.print"), Matcher.quoteReplacement("print"));
				FileWriter fileWriter = new FileWriter(filePath);
				bufferedWriter = new BufferedWriter(fileWriter);
				bufferedWriter.write(textStr);
				bufferedWriter.flush();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				bufferedReader = null;
			}
			if (bufferedWriter != null) {
				try {
					bufferedWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				bufferedReader = null;
			}

		}
	}
}
