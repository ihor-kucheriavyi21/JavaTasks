package com.task3.part1;

import com.task2.part2.IFineHandler;
import com.task2.part2.data.Fine;
import lombok.SneakyThrows;

import java.io.File;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FileParallelReader {

    private final IFineHandler fineHandler;

    public FileParallelReader(IFineHandler fineHandler) {
        this.fineHandler = fineHandler;
    }

    @SneakyThrows
    public List<Fine> readFinesFromDirectoryInParallel(String pathToDirectory, int countTreads) {
        File folder = new File(pathToDirectory);
        File[] listOfFiles = folder.listFiles();
        List<Fine> fineList = Collections.synchronizedList(new ArrayList<>());

        ExecutorService executorService = Executors.newFixedThreadPool(countTreads);
        CompletableFuture<Void> future = CompletableFuture.allOf(Arrays.stream(Objects.requireNonNull(listOfFiles))
                .map(xmlFile -> CompletableFuture
                        .supplyAsync(() -> fineHandler.getFinesFromXmlFile(xmlFile).getFineList(), executorService)
                        .thenAccept(fineList::addAll)).toArray(CompletableFuture[]::new));
        future.join();

        executorService.shutdown();

        executorService.awaitTermination(60, TimeUnit.SECONDS);
        return fineList;
    }
}
