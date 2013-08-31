package skynet.isi.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class Utils {

	public static void writeMsg(HttpServletResponse response, String msg){
		PrintWriter writer;
		try {
			writer = response.getWriter();
			writer.write(msg);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
