package lotto.model;

import lotto.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AnswerLotto {
	private final int START_RANGE = 1;
	private final int END_RANGE = 45;
	private final int SIZE = 6;
	List<Integer> answer;
	Integer bonus;

	public AnswerLotto(List<Integer> answer, Integer bonus) {
		isValidate(answer, bonus);
		this.answer = Util.getSortedList(answer);
		this.bonus = bonus;
	}

	private void isValidate(List<Integer> answer, Integer bonus){
		isAllCorrectRange(answer, bonus);
		LottoValidator.isCorrectSize(answer);
		LottoValidator.isDistinctNumbers(answer, bonus);
	}

	private void isAllCorrectRange(List<Integer> answer, Integer bonus) { // TODO : 수정
		List<Integer> joinNums = new ArrayList<>();
		joinNums.addAll(answer);
		joinNums.add(bonus);
		joinNums.stream()
				.forEach(LottoValidator::isCorrectRange);
	}



	public int getRank(Lotto lotto){
		int matchCount = getMatchCount(lotto.getNumbers());
		if (matchCount < 3) {
			return 6;
		}
		if (matchCount < 5 || (matchCount == 5 && !lotto.getNumbers().contains(bonus))) {
			matchCount--;
		}
		return Math.abs(7 - matchCount);
	}

	private int getMatchCount(List<Integer> numbers) {
		return (int) numbers.stream()
				.filter(num -> answer.contains(num) || num.equals(bonus))
				.count();
	}

}
