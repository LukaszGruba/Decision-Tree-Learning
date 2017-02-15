package com.lukgru.decision.tree.id3.io;

import com.lukgru.decision.tree.id3.algorithm.ID3;
import com.lukgru.decision.tree.id3.data.Instance;
import com.lukgru.decision.tree.id3.tree.DecisionTreeNode;
import org.junit.After;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Created by Łukasz on 2017-02-15.
 */
public class XmlTreeWriterIT {

    private static final String INPUT_CSV_PATH = "src/test/resources/wealth-training-data.csv";
    private static final String OUTPUT_XML_PATH = "src/test/resources/wealth-tree.xml";

    @After
    public void cleanUp() {
        new File(OUTPUT_XML_PATH).delete();
    }

    @Test
    public void test() throws IOException {
        //given
        TrainingDataLoader loader = new CsvTrainingDataLoader(INPUT_CSV_PATH);
        loader.load();
        Set<Instance> trainingDataSet = loader.getTrainingData();
        DecisionTreeNode root = new ID3().learn(trainingDataSet);

        //when
        new XmlTreeWriter().write(root, OUTPUT_XML_PATH);

        //then
        String expectedContent = getExpectedContent();
        Scanner stringScanner = new Scanner(expectedContent);
        Scanner fileScanner = new Scanner(new File(OUTPUT_XML_PATH).toPath());
        while (stringScanner.hasNextLine() || fileScanner.hasNextLine()) {
            assertEquals(stringScanner.nextLine(), fileScanner.nextLine());
        }
    }

    private String getExpectedContent() {
        return "<?xml version=\"1.0\"?>\n" +
                "<split attribute=\"City\">\n" +
                "\t<node>\n" +
                "\t\t<value>New York</value>\n" +
                "\t\t<decision>\n" +
                "\t\t\t<attribute>Status</attribute>\n" +
                "\t\t\t<value>rich</value>\n" +
                "\t\t</decision>\n" +
                "\t</node>\n" +
                "\t<node>\n" +
                "\t\t<value>Bialystok</value>\n" +
                "\t\t<decision>\n" +
                "\t\t\t<attribute>Status</attribute>\n" +
                "\t\t\t<value>poor</value>\n" +
                "\t\t</decision>\n" +
                "\t</node>\n" +
                "\t<node>\n" +
                "\t\t<value>Warszawa</value>\n" +
                "\t\t<split attribute=\"Car\">\n" +
                "\t\t\t<node>\n" +
                "\t\t\t\t<value>Audi</value>\n" +
                "\t\t\t\t<decision>\n" +
                "\t\t\t\t\t<attribute>Status</attribute>\n" +
                "\t\t\t\t\t<value>rich</value>\n" +
                "\t\t\t\t</decision>\n" +
                "\t\t\t</node>\n" +
                "\t\t\t<node>\n" +
                "\t\t\t\t<value>Skoda</value>\n" +
                "\t\t\t\t<split attribute=\"Sex\">\n" +
                "\t\t\t\t\t<node>\n" +
                "\t\t\t\t\t\t<value>f</value>\n" +
                "\t\t\t\t\t\t<decision>\n" +
                "\t\t\t\t\t\t\t<attribute>Status</attribute>\n" +
                "\t\t\t\t\t\t\t<value>rich</value>\n" +
                "\t\t\t\t\t\t</decision>\n" +
                "\t\t\t\t\t</node>\n" +
                "\t\t\t\t</split>\n" +
                "\t\t\t</node>\n" +
                "\t\t</split>\n" +
                "\t</node>\n" +
                "\t<node>\n" +
                "\t\t<value>Krakow</value>\n" +
                "\t\t<split attribute=\"Sex\">\n" +
                "\t\t\t<node>\n" +
                "\t\t\t\t<value>m</value>\n" +
                "\t\t\t\t<decision>\n" +
                "\t\t\t\t\t<attribute>Status</attribute>\n" +
                "\t\t\t\t\t<value>poor</value>\n" +
                "\t\t\t\t</decision>\n" +
                "\t\t\t</node>\n" +
                "\t\t\t<node>\n" +
                "\t\t\t\t<value>f</value>\n" +
                "\t\t\t\t<decision>\n" +
                "\t\t\t\t\t<attribute>Status</attribute>\n" +
                "\t\t\t\t\t<value>rich</value>\n" +
                "\t\t\t\t</decision>\n" +
                "\t\t\t</node>\n" +
                "\t\t</split>\n" +
                "\t</node>\n" +
                "\t<node>\n" +
                "\t\t<value>Berlin</value>\n" +
                "\t\t<decision>\n" +
                "\t\t\t<attribute>Status</attribute>\n" +
                "\t\t\t<value>rich</value>\n" +
                "\t\t</decision>\n" +
                "\t</node>\n" +
                "\t<node>\n" +
                "\t\t<value>Nowy Targ</value>\n" +
                "\t\t<decision>\n" +
                "\t\t\t<attribute>Status</attribute>\n" +
                "\t\t\t<value>poor</value>\n" +
                "\t\t</decision>\n" +
                "\t</node>\n" +
                "\t<node>\n" +
                "\t\t<value>Gdynia</value>\n" +
                "\t\t<decision>\n" +
                "\t\t\t<attribute>Status</attribute>\n" +
                "\t\t\t<value>rich</value>\n" +
                "\t\t</decision>\n" +
                "\t</node>\n" +
                "</split>";
    }

}