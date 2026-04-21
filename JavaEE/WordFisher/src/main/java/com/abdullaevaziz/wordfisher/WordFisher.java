package com.abdullaevaziz.wordfisher;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class WordFisher {

    // Please note these variables. they are the state of the object.
    public Map<String, Long> vocabulary;
    public List<String> stopWords; // User ArrayList for initialization
    private String inputTextFile;
    private String stopWordsFile;

    /**
     * 1a. public WordFisher(String inputTextFile, String stopwordsFile)
     * ● Этот конструктор получает имя входного файла и второе имя файла, содержащего стоп-слова.
     * Как обычно, все переменные-члены класса WordFisher должны быть инициализированы.
     * Переменные-члены этого класса (скопируйте их в точности!):
     * ○ public Map<String, Long> vocabulary
     * ○ private List<String> stopwords (для создания экземпляра как ArrayList)
     * ○ private String inputTextFile
     * ○ private String stopwordsFile
     */
    public WordFisher(String inputTextFile, String stopWordsFile) throws IOException {
        this.inputTextFile = inputTextFile;
        this.stopWordsFile = stopWordsFile;

        buildVocabulary();
        getStopWords();
    }

    /**
     * 1c. private void buildVocabulary()
     * ● Этот метод заполняет словарь карты из файла,
     * содержащего полный текст в формате обычного текста.
     * Обратите внимание, что каждое слово текста разделено пробелом.
     * Кроме того, текст может содержать небуквенно-цифровые символы,
     * такие как «?», «--» и «)», которые необходимо отфильтровать.
     * ● Вот способ, которым вы можете прочитать текст как String[]
     * с помощью Java Files and Paths: String reader =
     * new String(Files.readAllBytes(Paths.get(fileName)));
     * String[] allWords = reader.split(“\\s+");
     * // любое количество пробелов
     * ○ (
     * ○ Обязательно включите следующие импорты:
     * ○ import java.nio.file.Files;
     * ○ import java.nio.file.Paths;
     * ○ )
     */
    public void buildVocabulary() throws IOException {
        this.vocabulary = Files.lines(Paths.get(inputTextFile))
        .map(x -> x.replaceAll("[^a-zA-Z0-9 ] ", "")).
        map(x-> x.split(" ")).flatMap(Arrays :: stream).
                filter(x-> !x.isEmpty()).
        collect(Collectors.groupingBy(x -> x, Collectors.counting()));
    }

    /**
     * 1b. private void getStopwords()
     * ● Этот метод заполняет список стоп-слов из файла,
     * содержащего все стоп-слова, на которые указывает
     * переменная-член stopwordsFile.
     * Этот файл содержит по одному стоп-слову на строку.
     * Вы должны быть знакомы с тем,
     * как читать из файла из предыдущих лабораторных работ.
     */
    public void getStopWords() throws IOException {
       this.stopWords = Files.readAllLines(Path.of(stopWordsFile));
    }

    /**
     * 2. public int getWordCount()
     * ● Этот метод возвращает общее количество слов в тексте
     * и может быть получено с помощью словаря карты.
     * ● Общее количество слов в Moby Dick составляет 218 619. У Алисы их 27 336.
     */
    public long getWordCount() {
        // TODO: Return the total number of words in inputTextFile.
        // This can be calculated using vocabulary.
        //TODO сделать сумму всех значений из мапы
        return vocabulary.values().stream().mapToLong(Long::longValue).sum();
    }

    /**
     * 3. public int getNumUniqueWords()
     * ● Этот метод возвращает общее количество уникальных слов в тексте.
     * Это можно получить с помощью словаря карты.
     * ● Общее количество уникальных слов в Moby Dick
     * составляет 17 139, а у Алисы 2 570.
     */
    public int getNumUniqueWords() { //TODO здесь уникальные
        //TODO это значит количество различных слов то есть просто длина мапы
        // TODO: Return the number of unique words.
        // This should be the same as the number of keys in vocabulary.
        return vocabulary.size();
    }

    /**
     * 4. public int getFrequency(String word)
     * ● Возвращает частоту слова для заданного слова.
     * Это можно получить с помощью словаря карты.
     * Если слово отсутствует в словаре, следует вернуть -1.
     * ● Например, слово «кит» встречается 1226 раз в «Моби Дике»!
     * «носовой платок» встречается 5 раз в «Моби Дике»
     * и не встречается в «Алисе» (таким образом, возвращает -1).
     */
    public long getFrequency(String word) {
        // TODO: Return the number of times word occurs.
        // (Should be one simple line of code.)
        // Think about what vocabulary stores.
        return vocabulary.getOrDefault(word, -1L);
    }

    /**
     * 5. public void pruneVocabulary()
     * ● Этот метод удаляет все стоп-слова из словаря.
     * ● После обрезки getWordCount()
     * в «Моби Дике» возвращает 110 717 слов;
     * «Алиса» возвращает 12 241. (это очень много удаленных слов!)
     */
    public void pruneVocabulary() {
        // TODO: remove stopwords from the vocabulary.
        stopWords.forEach(vocabulary::remove);
    }

    /**
     * 6. public ArrayList<String> getTopWords(int n)
     * ● Этот метод получает целое число n и возвращает n самых часто
     * встречающихся слов в тексте в виде ArrayList строк.
     * ● При вызове getTopWords(10) для сокращенного словаря Моби Дика
     * возвращается следующий список: (что бы он вернул, если бы он не был сокращен?)
     * ○ [кит, один, как, на, человек, корабль, ахав, йе, море, старый]
     * ○ Мы узнаем, что киты, люди и корабли особенно важны в этой истории :-)
     * Что дает вам Алиса?
     * ○ Чтобы проверить результаты, вы можете написать вспомогательный метод,
     * который принимает этот результирующий список
     * в качестве входных данных и выводит связанную частоту.
     * Частоты должны отображаться в порядке убывания.
     */
    public List<String> getTopWords(int n) {
        // TODO: get the top n words.
        return  vocabulary.entrySet().
                stream().sorted((x1, x2) -> x2.getValue().compareTo(x1.getValue())).
                limit(n).map(Map.Entry :: getKey).collect(Collectors.toList());
    }

    /**
     * 7. public ArrayList<String> commonPopularWords(int n, WordFisher other)
     * ● Этот метод получает целое число n
     * и другой объект WordFisher (т. е. другой текст) в качестве входных данных
     * и возвращает ArrayList распространенных популярных слов
     * (взятых из n самых популярных из первого текста,
     * n самых популярных из другого) между двумя текстами.
     * Если общих слов нет, должен быть возвращен пустой список.
     * ● Например, вызов этого метода для сокращенного Моби Дика
     * с сокращенной Алисой и n = 20 дает… [one, like, would, time]
     */
    public List<String> commonPopularWords(int n, WordFisher other) {
        // TODO: get the common popular words.
        List<String> topWords1 = this.getTopWords(n);
        List<String> topWords2 = other.getTopWords(n);
        return topWords1.stream().filter(topWords2 :: contains).collect(Collectors.toList());
    }
}
