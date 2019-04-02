package Weather;
/**
 * @author zzc
 * 天气预报
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


import Listening.*;
import com.alibaba.fastjson.JSONObject;

public class WeatherDemo {
	private static String APIKEY = "c6d8aceb0150412d99ee99e5dfbadc3d";//官网注册后，换成你自己的
 
	public static void main(String[] args) throws IOException{
		
		Speak.Voice voice = new Speak.Voice();
		voice.strat("", 0);
        Audio.audio();
		String question = Audio.getRemoteRes();// 这是上传给云机器人的问题

		String INFO = null;
		String url = null;
		URL getUrl = null;
		HttpURLConnection connection = null;

		// 取得输入流，并使用Reader读取
		BufferedReader reader = null;
		StringBuffer sb = new StringBuffer();
		while (true){
			sb.setLength(0);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			question = Audio.getRemoteRes();// 这是上传给云机器人的问题
			//System.out.println("问题：" + question);
			if ("".equals(question) || "停".equals(question)){
				break;
			}
			if (question != null){
				INFO = URLEncoder.encode(question, "utf-8");
				url = "http://www.tuling123.com/openapi/api?key=" + APIKEY
						+ "&info=" + INFO;
				getUrl = new URL(url);
				connection = (HttpURLConnection) getUrl
						.openConnection();
				connection.connect();
				reader = new BufferedReader(
						new InputStreamReader(connection.getInputStream(), "utf-8"));
				String line = "";
				while ((line = reader.readLine()) != null) {
					sb.append(line);
				}
				reader.close();
				// 断开连接
				connection.disconnect();
				JSONObject jo = (JSONObject) JSONObject.parse(sb.toString());
				System.out.println("机器人：" + jo.get("text").toString());
			}

		}

	}

}
