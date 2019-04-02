package Listening;

import com.iflytek.cloud.speech.*;

/**
 *
 * �ϴ��ʻ��
 *      �ϴ��û��ʱ������ߴʱ��ڴʻ��ʶ���ʣ�Ҳ������������Ч����
 * ÿ���û��ն��豸��Ӧһ���ʱ��û��ʱ�ĸ�ʽ�����췽�������MSC Reference Manual��UserWords �ࡣ
 *      �Ը������ֱȽ���Ч������ Fa Yan�Ķ�������Ӧ�ĺ����� �����ԡ� �� �����ס� ��
 * ����� �����ס� �Ĵʻ����ʻ���������ȱ�ʶΪ���ף������� �����Ρ� Ҳ��
 */
public class UploadUserWords {
    private static final String APPID = "5c9cd8a7";
    private static final String USER_WORDS =
            "{\"userword\":[{\"name\":\"������ʻ�\",\"words\":[\"����洢��\",\"ֻ���洢��\",\"�����������\",\"�ֲ�����\",\"ѹ������\",\"ʮ�ߴ���ʾ��\"]}," +
                           "{\"name\":\"�ҵĴʻ�\",\"words\":[\"������\",\"���Ѿ�\",\"�ڵϴ�\",\"����\"]}]}";

    public static void main(String[] args) {
        uploadUserWords();
    }
    private static void uploadUserWords() {
        SpeechUtility.createUtility("appid=" + APPID);
        SpeechRecognizer recognizer = SpeechRecognizer.getRecognizer();
        if ( recognizer == null) {
            recognizer = SpeechRecognizer.createRecognizer();

            if( null == recognizer ){
                System.out.println("��ȡʶ��ʵ��ʵ�ܣ�");
                return;
            }
        }

        UserWords userwords = new UserWords(USER_WORDS);
        recognizer.setParameter( SpeechConstant.DATA_TYPE, "userword" );
        recognizer.updateLexicon("userwords",
                userwords.toString(),
                lexiconListener);
    }

    /**
     * �ʱ��ϴ�������
     */
    static LexiconListener lexiconListener = new LexiconListener() {
        @Override
        public void onLexiconUpdated(String lexiconId, SpeechError error) {
            if (error == null){
                System.out.println("*************�ϴ��ɹ�*************");
            }
            else{
                System.out.println("*************" + error.getErrorCode()
                        + "*************");
            }
        }

    };
}
