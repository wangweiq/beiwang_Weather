package com.beiwang.t1;

import java.io.InputStream;

import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import Listening.*;
import Speak.Voice;

public class Mian {
	
	public static void main(String[] args) throws Exception {
		Audio.audio();
		
		Scanner inputs = new Scanner(System.in);
		GetCityld idl = new GetCityld();
		System.out.println("请输入城市：");
		//String city = Audio.getRemoteRes();
		 String city ="房山";
		String code = GetCityld.getId(city);
		// 拼接地址
		String apiUrl = String.format("http://t.weather.sojson.com/api/weather/city/" + code);
		URL url = new URL(apiUrl);
		SslUtils.ignoreSsl();
		URLConnection open = url.openConnection();
		InputStream input = open.getInputStream();
		// 这里转换为String
		String result = org.apache.commons.io.IOUtils.toString(input, "utf-8");// 快速读取文件内容
		// 输出
		System.out.println(result);
		JSONObject jo = JSONObject.parseObject(result);
		JSONObject object1 = jo.getJSONObject("data");

		String s = JSON.toJSONString(object1);
		JSONObject j = JSONObject.parseObject(s);

		JSONObject object2 = jo.getJSONObject("cityInfo");
		System.out.println("天气更新时间：" + object2.getString("updateTime"));

		// 今天的天气情况
		JSONArray obj3 = j.getJSONArray("forecast");
		JSONObject object3 = (JSONObject) obj3.get(0);
		System.out.println(object3.getString("ymd") + "\t" + object3.getString("week"));
		System.out.println("当前城市：" + object2.getString("parent") + "\t" + "天气：" + object3.getString("type") + "\t\t"
				+ "日出：" + object3.getString("sunrise") + "\t\t" + "风向：" + object3.getString("fx"));
		System.out.println("空气质量：" + object1.getString("quality") + "\t\t" + "温度：" + object1.getString("wendu") + "℃"
				+ "\t\t" + "湿度：" + object1.getString("shidu") + "\t\t" + "日落：" + object3.getString("sunset") + "\t\t"
				+ "风力：" + object3.getString("fl"));
		System.out.println("PM2.5：" + object1.getString("pm25") + "\t\t" + "PM10：" + object1.getString("pm10") + "\t\t"
				+ "AQI：" + object3.getString("aqi"));
		System.out.println("最" + object3.getString("high") + "\t\t" + "最" + object3.getString("low"));
		System.out.println("感冒指数：" + object1.getString("ganmao"));
		System.out.println("小贴士：" + object3.getString("notice") + "\n");

		// 第二天的天气情况
		JSONObject object4 = (JSONObject) obj3.get(1);
		System.out.println(object4.getString("date") + "日" + "\t" + object4.getString("week"));
		System.out.println("天气：" + object4.getString("type"));
		System.out.println("最" + object4.getString("high"));
		System.out.println("最" + object4.getString("low"));
		System.out.println("风力：" + object4.getString("fl"));
		System.out.println("风向：" + object4.getString("fx"));
		System.out.println("AQI：" + object4.getString("aqi"));
		System.out.println("小贴士：" + object4.getString("notice") + "\n");

		// 第三天的天气情况
		JSONObject object5 = (JSONObject) obj3.get(2);
		System.out.println(object5.getString("date") + "日" + "\t" + object4.getString("week"));
		System.out.println("天气：" + object5.getString("type"));
		System.out.println("最" + object5.getString("high"));
		System.out.println("最" + object5.getString("low"));
		System.out.println("风力：" + object5.getString("fl"));
		System.out.println("风向：" + object5.getString("fx"));
		System.out.println("AQI：" + object5.getString("aqi"));
		System.out.println("小贴士：" + object5.getString("notice") + "\n");

		// 第四天的天气情况
		JSONObject object6 = (JSONObject) obj3.get(3);
		System.out.println(object6.getString("date") + "日" + "\t" + object4.getString("week"));
		System.out.println("天气：" + object6.getString("type"));
		System.out.println("最" + object6.getString("high"));
		System.out.println("最" + object6.getString("low"));
		System.out.println("风力：" + object6.getString("fl"));
		System.out.println("风向：" + object6.getString("fx"));
		System.out.println("AQI：" + object6.getString("aqi"));
		System.out.println("小贴士：" + object6.getString("notice") + "\n");

		// 语音播报当天的天气情况
		Voice voice = new Voice();
		voice.strat("您好！您所在的城市为" + object2.getString("parent"), 0);
		voice.strat("天气：" + object3.getString("type"), 0);
		voice.strat("空气质量：" + object1.getString("quality"), 0);
		voice.strat("空气湿度：" + object1.getString("shidu"), 0);
		voice.strat("现在温度：" + object1.getString("wendu") + "℃", 0);
		voice.strat("当天的" + "最" + object3.getString("high"), 0);
		voice.strat( "最" + object3.getString("low"), 0);
		voice.strat("风向：" + object3.getString("fx"), 0);
		voice.strat("风力：" + object3.getString("fl"), 0);
		voice.strat("感冒指数：" + object1.getString("ganmao"), 0);
		voice.strat("PM2.5为" + object1.getString("pm25"), 0);
		voice.strat("PM10为" + object1.getString("pm10"), 0);
		voice.strat("AQI为" + object3.getString("aqi"), 0);
		voice.strat("小贴士：" + object3.getString("notice"), 0);
		
	}
}
