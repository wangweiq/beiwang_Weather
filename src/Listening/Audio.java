package Listening;

import com.iflytek.cloud.speech.SpeechConstant;
import com.iflytek.cloud.speech.SpeechRecognizer;
import com.iflytek.cloud.speech.SpeechUtility;

/**
 * 
 * @author zzc
 *������д
 *
 */
public class Audio {



	private static final String APPID = "5c9cd8a7";//��ʼ��
	private static Recognizer recognizer;

	public static  void audio() {
		SpeechUtility.createUtility("appid=" + APPID);
		//�����û��������ö����ſ���ʹ���������񣬽����ڳ�����ڴ����á� ���ڳ�ʼ��ʱָ��������
		//�򱨼��ؿ�ʧ�ܵĽ���취����ο���MSC Reference Manual���У� ���� SpeechUtility �࣬
		//�Լ� SpeechConstant ���˵��
		//1.����SpeechRecognizer����
		SpeechRecognizer mIat= SpeechRecognizer.createRecognizer( );

		//2.������д�����������MSC Reference Manual��SpeechConstant��
		//iat��Ĭ���ճ�����

		// ����������˵�
		mIat.setParameter(SpeechConstant.VAD_EOS, "4000");
		mIat.setParameter(SpeechConstant.DOMAIN, "iat");
		//�������ģ�zh_cn,Ӣ�ģ�en_us
		mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
		//��ͨ��mandarin��cantonese�Ĵ��������ϻ���henanese
		mIat.setParameter(SpeechConstant.ACCENT, "mandarin");
		//ʶ����ɺ��ڱ��ر���һ��.pcm����Ƶ�ļ�
		mIat.setParameter(SpeechConstant.ASR_AUDIO_PATH, "./tts_test.pcm");
		//��дĬ����1��1�Ǵ���˷��ȡ������-1�Ǵ�.pcm��Ƶ�ļ���ȡ�ļ�
		mIat.setParameter(SpeechConstant.AUDIO_SOURCE, "1");
		//3.��ʼ��д
		recognizer =new Recognizer();
		mIat.startListening(recognizer);
	}


	public static String getRemoteRes(){
		String res = recognizer.getRes();
		recognizer.setStatus(false);
		return res;
	}



}
