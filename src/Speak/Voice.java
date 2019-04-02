package Speak;



import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class Voice {
	public void strat(String content, int type) {
		ActiveXComponent sap = new ActiveXComponent("Sapi.SpVoice");
		Dispatch sapo = sap.getObject();
		if (type == 0) {
			try {
				// “Ù¡ø0-100
				sap.setProperty("Volume", new Variant(100));
				// ¿ ∂¡ÀŸ∂»-10µΩ10
				sap.setProperty("Rate", new Variant(-0.5));
				Variant defalutVoice = sap.getProperty("Voice");

				Dispatch dispdefaultVoice = defalutVoice.toDispatch();
				Variant allVoices = Dispatch.call(sapo, "GetVoices");
				Dispatch dispVoices = allVoices.toDispatch();

				Dispatch setvoice = Dispatch.call(dispVoices, "Item", new Variant(1)).toDispatch();
				ActiveXComponent voiceActivex = new ActiveXComponent(dispdefaultVoice);
				ActiveXComponent setvoiceActivex = new ActiveXComponent(setvoice);

				Variant item = Dispatch.call(setvoiceActivex, "GetDescription");
				// ÷¥––¿ ∂¡
				Dispatch.call(sapo, "Speak", new Variant(content));

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} finally {
				sapo.safeRelease();
				sap.safeRelease();
			}
		} else {
			// Õ£÷π
			try {
				Dispatch.call(sapo, "Speak", new Variant(content), new Variant(2));
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}
}
