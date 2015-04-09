package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextFile extends ArrayList<String> {
	
	private static final long serialVersionUID = 1L;
	
	private StringBuffer file = new StringBuffer() ; 

	public TextFile(String filePath, String regex) {
		filePath = "G:\\workspaceSTS\\thinkingInJava\\test\\" + filePath ;
		File file = new File(filePath);
		if(file.canRead()) {
			FileInputStream fileInputStream = null;
			try {
				fileInputStream = new FileInputStream(file);
				InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
				Pattern pattern = Pattern.compile("(" + regex + ")");
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
				try {
//					readChar(inputStreamReader, pattern);
					readLine(bufferedReader, pattern);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally {
				if(fileInputStream != null ) {
					try {
						fileInputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					fileInputStream = null;
				}
			}
		}
	}
	
	@SuppressWarnings("unused")
	private void readChar(InputStreamReader inputStreamReader, Pattern pattern) throws IOException {
		Matcher matcher;
		String str = "";
		int i = -1;
		while((i=inputStreamReader.read()) != -1) {
			char c = (char)i;
			matcher = pattern.matcher(String.valueOf(c));
			if(matcher.matches()) {
				str += c ;
			} else {
				if(str != "") {
					add(str);
					str = ""; 	
				}
			}
			
		}
	}
	
	private void readLine(BufferedReader bufferedReader, Pattern pattern) throws IOException {
		Matcher matcher;
		String str = "";
		while((str=bufferedReader.readLine()) != null) {
			matcher = pattern.matcher(str);
			while(matcher.find()) {
				add(matcher.group());
			}
			file.append(str);
		}
	}
	
	//获得整个文本内容，Ex25+使用
	public String getFileText() {
		return this.file.toString();
	}
	
}
