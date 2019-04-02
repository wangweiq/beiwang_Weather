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
		System.out.println("��������У�");
		//String city = Audio.getRemoteRes();
		 String city ="��ɽ";
		String code = GetCityld.getId(city);
		// ƴ�ӵ�ַ
		String apiUrl = String.format("http://t.weather.sojson.com/api/weather/city/" + code);
		URL url = new URL(apiUrl);
		SslUtils.ignoreSsl();
		URLConnection open = url.openConnection();
		InputStream input = open.getInputStream();
		// ����ת��ΪString
		String result = org.apache.commons.io.IOUtils.toString(input, "utf-8");// ���ٶ�ȡ�ļ�����
		// ���
		System.out.println(result);
		JSONObject jo = JSONObject.parseObject(result);
		JSONObject object1 = jo.getJSONObject("data");

		String s = JSON.toJSONString(object1);
		JSONObject j = JSONObject.parseObject(s);

		JSONObject object2 = jo.getJSONObject("cityInfo");
		System.out.println("��������ʱ�䣺" + object2.getString("updateTime"));

		// ������������
		JSONArray obj3 = j.getJSONArray("forecast");
		JSONObject object3 = (JSONObject) obj3.get(0);
		System.out.println(object3.getString("ymd") + "\t" + object3.getString("week"));
		System.out.println("��ǰ���У�" + object2.getString("parent") + "\t" + "������" + object3.getString("type") + "\t\t"
				+ "�ճ���" + object3.getString("sunrise") + "\t\t" + "����" + object3.getString("fx"));
		System.out.println("����������" + object1.getString("quality") + "\t\t" + "�¶ȣ�" + object1.getString("wendu") + "��"
				+ "\t\t" + "ʪ�ȣ�" + object1.getString("shidu") + "\t\t" + "���䣺" + object3.getString("sunset") + "\t\t"
				+ "������" + object3.getString("fl"));
		System.out.println("PM2.5��" + object1.getString("pm25") + "\t\t" + "PM10��" + object1.getString("pm10") + "\t\t"
				+ "AQI��" + object3.getString("aqi"));
		System.out.println("��" + object3.getString("high") + "\t\t" + "��" + object3.getString("low"));
		System.out.println("��ðָ����" + object1.getString("ganmao"));
		System.out.println("С��ʿ��" + object3.getString("notice") + "\n");

		// �ڶ�����������
		JSONObject object4 = (JSONObject) obj3.get(1);
		System.out.println(object4.getString("date") + "��" + "\t" + object4.getString("week"));
		System.out.println("������" + object4.getString("type"));
		System.out.println("��" + object4.getString("high"));
		System.out.println("��" + object4.getString("low"));
		System.out.println("������" + object4.getString("fl"));
		System.out.println("����" + object4.getString("fx"));
		System.out.println("AQI��" + object4.getString("aqi"));
		System.out.println("С��ʿ��" + object4.getString("notice") + "\n");

		// ��������������
		JSONObject object5 = (JSONObject) obj3.get(2);
		System.out.println(object5.getString("date") + "��" + "\t" + object4.getString("week"));
		System.out.println("������" + object5.getString("type"));
		System.out.println("��" + object5.getString("high"));
		System.out.println("��" + object5.getString("low"));
		System.out.println("������" + object5.getString("fl"));
		System.out.println("����" + object5.getString("fx"));
		System.out.println("AQI��" + object5.getString("aqi"));
		System.out.println("С��ʿ��" + object5.getString("notice") + "\n");

		// ��������������
		JSONObject object6 = (JSONObject) obj3.get(3);
		System.out.println(object6.getString("date") + "��" + "\t" + object4.getString("week"));
		System.out.println("������" + object6.getString("type"));
		System.out.println("��" + object6.getString("high"));
		System.out.println("��" + object6.getString("low"));
		System.out.println("������" + object6.getString("fl"));
		System.out.println("����" + object6.getString("fx"));
		System.out.println("AQI��" + object6.getString("aqi"));
		System.out.println("С��ʿ��" + object6.getString("notice") + "\n");

		// ��������������������
		Voice voice = new Voice();
		voice.strat("���ã������ڵĳ���Ϊ" + object2.getString("parent"), 0);
		voice.strat("������" + object3.getString("type"), 0);
		voice.strat("����������" + object1.getString("quality"), 0);
		voice.strat("����ʪ�ȣ�" + object1.getString("shidu"), 0);
		voice.strat("�����¶ȣ�" + object1.getString("wendu") + "��", 0);
		voice.strat("�����" + "��" + object3.getString("high"), 0);
		voice.strat( "��" + object3.getString("low"), 0);
		voice.strat("����" + object3.getString("fx"), 0);
		voice.strat("������" + object3.getString("fl"), 0);
		voice.strat("��ðָ����" + object1.getString("ganmao"), 0);
		voice.strat("PM2.5Ϊ" + object1.getString("pm25"), 0);
		voice.strat("PM10Ϊ" + object1.getString("pm10"), 0);
		voice.strat("AQIΪ" + object3.getString("aqi"), 0);
		voice.strat("С��ʿ��" + object3.getString("notice"), 0);
		
	}
}
