package com.abdullaevaziz.wordfisher;


import java.io.IOException;
import java.util.List;

public class WordFisherTester {

	public static void main(String[] args) {

		try {
			WordFisher alice = new WordFisher("texts/carroll-alice.txt", "stopwords.txt");
			WordFisher moby = new WordFisher("texts/moby-dick.txt", "stopwords.txt");

			long res1 = alice.getWordCount();
			System.out.println("Общее количество слов в тексте: " + res1);
			System.out.println();
			int res3 = alice.getNumUniqueWords();
			System.out.println("Общее количество уникальных слов в тексте: " + res3);
			System.out.println();
			long res4 = alice.getFrequency("one");
			System.out.println("Задано слово, сколько раз оно встречается в тексте: " + res4);
			System.out.println();
			List<String> res5 = alice.getTopWords(10);
			System.out.println("Каковы 10 наиболее часто встречающихся слов в тексте: " + res5);
			System.out.println();
			List<String> res6 = alice.commonPopularWords(90,moby);
			System.out.println("Каковы наиболее распространенные популярные слова между двумя текстами: " + res6);
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
