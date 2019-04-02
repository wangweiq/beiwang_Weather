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
	 //开始录音
    public void onBeginOfSpeech() {
        System.out.println(".。开始录音。.");
    }
    //音量值0~30
    public void onVolumeChanged(int volume){
            /*System.out.println("当前音量"+volume);*/
    }
    //结束录音
    public void onEndOfSpeech() {
        System.out.println("录音结束");
    }
    //扩展用接口
    public void onEvent(int eventType,int arg1,int arg2,String msg) {}
    //听写结果回调接口(返回Json格式结果，用户可参见附录)；
    //一般情况下会通过onResults接口多次返回结果，完整的识别内容是多次结果的累加；
    //关于解析Json的代码可参见MscDemo中JsonParser类；
    //isLast等于true时会话结束。
    public void onResult(RecognizerResult results, boolean isLast){
    	//使用了阿里巴巴fastjson的架包来解析返回的json字符串。
		//对于这部分可能大家要看看fastjson的使用方法才看得懂哦
        StringBuffer sb = new StringBuffer();
		Root root=JSON.parseObject(results.getResultString(), Root.class);
		Iterator<Ws> list=root.getWs().iterator();
		while(list.hasNext()){
			Iterator<Cw> listCw=list.next().getCw().iterator();
			while(listCw.hasNext()){
				
				String a = listCw.next().getW();
				
		        String regEx = "[`~!@#$%^&*()\\-+={}':;,\\[\\].<>/?￥%…（）_+|【】‘；：”“’。，、？\\s]";
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
        //json结果实例
        //Result:{"sn":1,"ls":false,"bg":0,"ed":0,"ws":[{"bg":0,"cw":[{"sc":0.00,"w":"你好"}]}]}
        //sn:第几句
        //ls:是否是第一句
        //bg：开始
        //ed:结束
        //ws:词语
        //cw:中文分词
        //w:单词
        //sc:分数
	
    }
    public void onError(SpeechError error) {
        // error.getPlainDescription(true); //获取错误码描述
        System.out.println(error.getErrorDesc());
       
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
