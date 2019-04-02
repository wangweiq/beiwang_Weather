package Listening;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSON;
import com.iflytek.cloud.speech.RecognizerListener;
import com.iflytek.cloud.speech.RecognizerResult;
import com.iflytek.cloud.speech.SpeechError;

public class Recognizer implements RecognizerListener{
    private String res;



    private Boolean status = false;



    public String getRes() {
        if (this.status){
            return res;
        }else{
            return null;
        }
    }

    public void setRes(String res) {
        this.res = res;
    }
	 //��ʼ¼��
    public void onBeginOfSpeech() {
        System.out.println(".����ʼ¼����.");
    }
    //����ֵ0~30
    public void onVolumeChanged(int volume){
            /*System.out.println("��ǰ����"+volume);*/
    }
    //����¼��
    public void onEndOfSpeech() {
        System.out.println("¼������");
    }
    //��չ�ýӿ�
    public void onEvent(int eventType,int arg1,int arg2,String msg) {}
    //��д����ص��ӿ�(����Json��ʽ������û��ɲμ���¼)��
    //һ������»�ͨ��onResults�ӿڶ�η��ؽ����������ʶ�������Ƕ�ν�����ۼӣ�
    //���ڽ���Json�Ĵ���ɲμ�MscDemo��JsonParser�ࣻ
    //isLast����trueʱ�Ự������
    public void onResult(RecognizerResult results, boolean isLast){
    	//ʹ���˰���Ͱ�fastjson�ļܰ����������ص�json�ַ�����
		//�����ⲿ�ֿ��ܴ��Ҫ����fastjson��ʹ�÷����ſ��ö�Ŷ
        StringBuffer sb = new StringBuffer();
		Root root=JSON.parseObject(results.getResultString(), Root.class);
		Iterator<Ws> list=root.getWs().iterator();
		while(list.hasNext()){
			Iterator<Cw> listCw=list.next().getCw().iterator();
			while(listCw.hasNext()){
				
				String a = listCw.next().getW();
				
		        String regEx = "[`~!@#$%^&*()\\-+={}':;,\\[\\].<>/?��%������_+|������������������������\\s]";
		        Pattern p = Pattern.compile(regEx);
		        Matcher m = p.matcher(a);
		        String VoiceInput= m.replaceAll("");
		        System.out.print(VoiceInput);
		        sb.append(VoiceInput);
			}
            this.res = sb.toString();
        }
        this.status = true;
		System.out.println();
    	//System.out.println("Result:"+results.getResultString ());
        //json���ʵ��
        //Result:{"sn":1,"ls":false,"bg":0,"ed":0,"ws":[{"bg":0,"cw":[{"sc":0.00,"w":"���"}]}]}
        //sn:�ڼ���
        //ls:�Ƿ��ǵ�һ��
        //bg����ʼ
        //ed:����
        //ws:����
        //cw:���ķִ�
        //w:����
        //sc:����
	
    }
    public void onError(SpeechError error) {
        // error.getPlainDescription(true); //��ȡ����������
        System.out.println(error.getErrorDesc());
       
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
