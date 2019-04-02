package Listening;

import com.iflytek.cloud.speech.SpeechConstant;
import com.iflytek.cloud.speech.SpeechRecognizer;
import com.iflytek.cloud.speech.SpeechUtility;

/**
 * 
 * @author zzc
 *语音听写
 *
 */
public class Audio {



	private static final String APPID = "5c9cd8a7";//初始化
	private static Recognizer recognizer;

	public static  void audio() {
		SpeechUtility.createUtility("appid=" + APPID);
		//创建用户语音配置对象后才可以使用语音服务，建议在程序入口处调用。 关于初始化时指定库名，
		//或报加载库失败的解决办法，请参考《MSC Reference Manual》中， 关于 SpeechUtility 类，
		//以及 SpeechConstant 类的说明
		//1.创建SpeechRecognizer对象
		SpeechRecognizer mIat= SpeechRecognizer.createRecognizer( );

		//2.设置听写参数，详见《MSC Reference Manual》SpeechConstant类
		//iat是默认日常用语

		// 设置语音后端点
		mIat.setParameter(SpeechConstant.VAD_EOS, "4000");
		mIat.setParameter(SpeechConstant.DOMAIN, "iat");
		//简体中文：zh_cn,英文：en_us
		mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
		//普通话mandarin，cantonese四川话，河南话：henanese
		mIat.setParameter(SpeechConstant.ACCENT, "mandarin");
		//识别完成后在本地保存一个.pcm的音频文件
		mIat.setParameter(SpeechConstant.ASR_AUDIO_PATH, "./tts_test.pcm");
		//不写默认是1，1是从麦克风读取声音，-1是从.pcm音频文件读取文件
		mIat.setParameter(SpeechConstant.AUDIO_SOURCE, "1");
		//3.开始听写
		recognizer =new Recognizer();
		mIat.startListening(recognizer);
	}


	public static String getRemoteRes(){
		String res = recognizer.getRes();
		recognizer.setStatus(false);
		return res;
	}



}
