package Weather;
/**
 * @author zzc
 * ����Ԥ��
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
	private static String APIKEY = "c6d8aceb0150412d99ee99e5dfbadc3d";//����ע��󣬻������Լ���
 
	public static void main(String[] args) throws IOException{
		
		Speak.Voice voice = new Speak.Voice();
		voice.strat("", 0);
        Audio.audio();
		String question = Audio.getRemoteRes();// �����ϴ����ƻ����˵�����

		String INFO = null;
		String url = null;
		URL getUrl = null;
		HttpURLConnection connection = null;

		// ȡ������������ʹ��Reader��ȡ
		BufferedReader reader = null;
		StringBuffer sb = new StringBuffer();
		while (true){
			sb.setLength(0);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			question = Audio.getRemoteRes();// �����ϴ����ƻ����˵�����
			//System.out.println("���⣺" + question);
			if ("".equals(question) || "ͣ".equals(question)){
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
				// �Ͽ�����
				connection.disconnect();
				JSONObject jo = (JSONObject) JSONObject.parse(sb.toString());
				System.out.println("�����ˣ�" + jo.get("text").toString());
			}

		}

	}

}
