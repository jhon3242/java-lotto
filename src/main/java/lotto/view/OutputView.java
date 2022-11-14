package lotto.view;

import lotto.Message;
import lotto.Prize;
import lotto.model.Lotto;

import java.util.Collections;
import java.util.List;

public class OutputView {
	private static final int LOTTO_COST = 1000;

	public static void printLottos(List<Lotto> lottos) {
		System.out.println("" + lottos.size() + Message.AFTER_BUY.getMessage());
		lottos.stream()
				.forEach(System.out::println);
	}

	public static void printPrize(List<Integer> prizes) {
		System.out.println(Message.BEFORE_PRIZE.getMessage());
		for (int rank = Prize.FIFTH.getRank(); rank >= 1; rank--) {
			int matchCount = prizes.get(rank - 1);
			Prize prize = Prize.findByRank(rank);
			System.out.println(String.format(prize.getMessage(), matchCount));
		}
	}

	public static void printProfit(List<Integer> prizes) {
		float profit = getProfit(prizes);
		String profitMessage = String.format(Message.PROFIT.getMessage(), profit);
		System.out.println(profitMessage);
	}

	private static float getProfit(List<Integer> prizes) {
		Long totalPrize = getTotalPrize(prizes);
		int totalCount = prizes.stream()
				.reduce((x, y) -> x + y)
				.get();
		return ((float) totalPrize / (LOTTO_COST * totalCount)) * 100;
	}

	private static Long getTotalPrize(List<Integer> prizes) {
		Long totalPrize = 0L;
		for (int rank = Prize.FIRST.getRank(); rank <= Prize.FIFTH.getRank(); rank++) {
			Prize prize = Prize.findByRank(rank);
			int count = prizes.get(rank - 1);
			totalPrize += (long) prize.getMoney() * count;
		}
		return totalPrize;
	}
}
