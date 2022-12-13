package com.task3.part1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.task2.part2.FineCounter;
import com.task2.part2.FineHandler;
import com.task3.part1.FileParallelReader;
import lombok.SneakyThrows;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import java.io.File;
import java.util.Map;

public class MultithreadingFileReaderTest {

    private static final String INPUT_DIRECTORY_PATH = "src/test/resources/task3/part1/input";
    private static final String RESULT_FILE_PATH = "src/test/resources/task3/part1/output/result.json";
    private static final String RESULT_FILE_PATH_2 = "src/test/resources/task3/part1/output/result2.json";
    private static final String RESULT_FILE_PATH_4 = "src/test/resources/task3/part1/output/result4.json";
    private static final String RESULT_FILE_PATH_8 = "src/test/resources/task3/part1/output/result8.json";

    /**
     * Execution time is decreased twice every time the threads are doubled
     */
    @Test
    public void testMultiThreadingReading() {
        FileParallelReader fileParallelReader = new FileParallelReader(new FineHandler());

        long timeFor1Threads = System.currentTimeMillis();
        fileParallelReader.readFinesFromDirectoryInParallel(INPUT_DIRECTORY_PATH, 1);
        long timeOnReadingFilesIn1Threads = System.currentTimeMillis() - timeFor1Threads;

        long timeFor2Threads = System.currentTimeMillis();
        fileParallelReader.readFinesFromDirectoryInParallel(INPUT_DIRECTORY_PATH, 2);
        long timeOnReadingFilesIn2Threads = System.currentTimeMillis() - timeFor2Threads;

        long timeFor4Threads = System.currentTimeMillis();
        fileParallelReader.readFinesFromDirectoryInParallel(INPUT_DIRECTORY_PATH, 4);
        long timeOnReadingFilesIn4Threads = System.currentTimeMillis() - timeFor4Threads;

        long timeFor8Threads = System.currentTimeMillis();
        fileParallelReader.readFinesFromDirectoryInParallel(INPUT_DIRECTORY_PATH, 8);
        long timeOnReadingFilesIn8Threads = System.currentTimeMillis() - timeFor8Threads;
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(timeOnReadingFilesIn8Threads < timeOnReadingFilesIn4Threads)
                .isTrue();
        softAssertions.assertThat(timeOnReadingFilesIn4Threads < timeOnReadingFilesIn2Threads)
                .isTrue();
        softAssertions.assertThat(timeOnReadingFilesIn2Threads < timeOnReadingFilesIn1Threads)
                .isTrue();
        softAssertions.assertAll();
    }

    @SneakyThrows
    @Test
    public void testCorrectSaving() {
        FileParallelReader fileParallelReader = new FileParallelReader(new FineHandler());
        ObjectMapper mapper = new ObjectMapper();

        Map<String, Double> resultFor1Threads = new FineCounter().sortAndCountFineAmount(
                fileParallelReader.readFinesFromDirectoryInParallel(INPUT_DIRECTORY_PATH, 1));

        Map<String, Double> resultFor2Threads = new FineCounter().sortAndCountFineAmount(
                fileParallelReader.readFinesFromDirectoryInParallel(INPUT_DIRECTORY_PATH, 2));
        Map<String, Double> resultFor4Threads = new FineCounter().sortAndCountFineAmount(
                fileParallelReader.readFinesFromDirectoryInParallel(INPUT_DIRECTORY_PATH, 4));
        Map<String, Double> resultFor8Threads = new FineCounter().sortAndCountFineAmount(
                fileParallelReader.readFinesFromDirectoryInParallel(INPUT_DIRECTORY_PATH, 8));

        File resultFile1 = new File(RESULT_FILE_PATH);
        mapper.writeValue(resultFile1, resultFor1Threads);
        File resultFile2 = new File(RESULT_FILE_PATH_2);
        mapper.writeValue(resultFile2, resultFor2Threads);
        File resultFile4 = new File(RESULT_FILE_PATH_4);
        mapper.writeValue(resultFile4, resultFor4Threads);
        File resultFile8 = new File(RESULT_FILE_PATH_8);
        mapper.writeValue(resultFile8, resultFor8Threads);
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(resultFile1).hasSameTextualContentAs(resultFile2);
        softAssertions.assertThat(resultFile2).hasSameTextualContentAs(resultFile4);
        softAssertions.assertThat(resultFile4).hasSameTextualContentAs(resultFile8);
        softAssertions.assertAll();
    }

}
