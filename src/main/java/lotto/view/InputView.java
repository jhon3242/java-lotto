package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.Message;

public class InputView {

	public static String buyInput(){
		System.out.println(Message.BEFORE_BYE);
		return Console.readLine();
	}
}