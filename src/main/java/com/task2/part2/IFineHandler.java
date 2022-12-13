package com.task2.part2;

import com.task2.part2.data.Fines;

import java.io.File;

public interface IFineHandler {

     Fines getFinesFromXmlFile(File file);
}
