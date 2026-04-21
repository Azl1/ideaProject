package com.abdullaevaziz.repository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MobileRepository {

    private Map<String, List<String>> stringListMap;
    private File file;

    public MobileRepository(File file) throws IOException {
        this.file = file;
        this.stringListMap = Files.lines(file.toPath()).
                filter(x -> !x.isEmpty() && x.split("\\|")[4].equals("FRAUD"))
                .collect(Collectors.groupingBy(x -> x.split("\\|")[2], Collectors.mapping(x -> x, Collectors.toList())));
    }

    public void save(File root) throws IOException {
        File processedData = new File(root, "processed_data");
        processedData.mkdirs();

        for (var value : stringListMap.entrySet()) {
            /**
             * • на основании имеющихся файлов сгенерировать новые файлы и записать
             * их в соответствующую папку с названием ОПЕРАТОРА, находящихся в папке processed_data
             */
            String operator = value.getKey();
            File fileOperator = new File(processedData, operator);
            fileOperator.mkdirs();

            /**
             * • каждый новый файл должен иметь следующее название
             * ОПЕРАТОР_FRAUD_LIST_yyyyMMdd_*.0.txt, где *.0
             * - порядковый номер файла в папке ОПЕРАТОРА
             */
            int number = Arrays.stream(fileOperator.listFiles())
                    .mapToInt(x -> Integer.parseInt(x.getName().split("_")[4]
                            .replace(".txt", ""))).max().orElse(0) + 1;

            //TODO дата берется из оригнальногго имени файла из исходного она там есть на какой-то позиции
            String date = file.getName().split("_")[3];



            List<String> strings = value.getValue();
            String fileName = operator + "_FRAUD_LIST_"
                    + date + "_" + number + ".txt";
            File newFile = new File(fileOperator, fileName);

            /**
             * • в каждый новый файл вносится информация
             * из соответствующего ему файлу по дате,
             * игнорируя данные, в столбцах которых указано NO_FRAUD,
             * группируя данные по каждому оператору ОТДЕЛЬНО.
             * Нумерация в строках в новом файле должна начинаться с 1.
             */
            try (BufferedWriter bufferedWriter
                         = new BufferedWriter(new FileWriter(newFile))) {
                int[] countArray = new int[]{1};
                for (String line : strings) {
                    String split[] = line.split("\\|");
                    split[0] = String.valueOf(countArray[0]);
                    countArray[0]++;
                    String joinRes = String.join("|", split);
                    bufferedWriter.write(joinRes);
                    bufferedWriter.newLine();
                }
            }

        }
    }

    @Override
    public String toString() {
        return "MobileRepository{" +
                "stringListMap=" + stringListMap +
                ", file=" + file +
                '}';
    }
}
