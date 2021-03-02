import java.io.*;
import parcs.*;


import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args) throws IOException {
        task curtask = new task();
        curtask.addJarFile("Algo.jar");

        AMInfo info = new AMInfo(curtask, null);

        String[] positives = readLines("positives.txt");
        String[] negatives = readLines("negatives.txt");
        String[] sports = readLines("sports.txt");
        String[] covid = readLines("covid.txt");
        String[] aviation = readLines("aviation.txt");

        System.out.println("Start executing");
        long startTime = System.nanoTime();

        List<channel> channels = new ArrayList<>();
            String path = "";
            path = "input1.txt";
            String article1 = read(path);
            point p1 = info.createPoint();
            channel c1 = p1.createChannel();
            p1.execute("Algo");
            c1.write(article1);
            c1.write(positives);
            c1.write(negatives);
            c1.write(sports);
            c1.write(covid);
            c1.write(aviation);
            channels.add(c1);
        
            path = "input2.txt";
            String article2 = read(path);
            point p2 = info.createPoint();
            channel c2 = p2.createChannel();
            p2.execute("Algo");
            c2.write(article2);
            c2.write(positives);
            c2.write(negatives);
            c2.write(sports);
            c2.write(covid);
            c2.write(aviation);
            channels.add(c2);
        
            path = "input3.txt";
            String article3 = read(path);
            point p3 = info.createPoint();
            channel c3 = p3.createChannel();
            p3.execute("Algo");
            c3.write(article3);
            c3.write(positives);
            c3.write(negatives);
            c3.write(sports);
            c3.write(covid);
            c3.write(aviation);
            channels.add(c3);
        
            path = "input4.txt";
            String article4 = read(path);
            point p4 = info.createPoint();
            channel c4 = p4.createChannel();
            p4.execute("Algo");
            c4.write(article4);
            c4.write(positives);
            c4.write(negatives);
            c4.write(sports);
            c4.write(covid);
            c4.write(aviation);
            channels.add(c4);
        
            path = "input5.txt";
            String article5 = read(path);
            point p5 = info.createPoint();
            channel c5 = p5.createChannel();
            p5.execute("Algo");
            c5.write(article5);
            c5.write(positives);
            c5.write(negatives);
            c5.write(sports);
            c5.write(covid);
            c5.write(aviation);
            channels.add(c5);
        
        

        /*for (int i = 0; i < 5; i++) {
            String path = "input" + String.valueOf(i) + ".txt";
            String article = read(path);
            point p = info.createPoint();
            channel c = p.createChannel();
            p.execute("Algo");
            c.write(article);
            c.write(positives);
            c.write(negatives);
            c.write(sports);
            c.write(covid);
            c.write(aviation);
            channels.add(c);
        }*/


        for (parcs.channel channel : channels) {
            System.out.println("Waiting for result...");

            String topic = (String) channel.readObject();
            String sentiment = (String) channel.readObject();
            System.out.println("Result found:");
            System.out.println(topic);
            System.out.println(sentiment);

        }

        double estimatedTime = (double) (System.nanoTime() - startTime) / 1000000000;
        System.out.println("Time total (excluding IO): " + estimatedTime);

        curtask.end();
    }


    public static String read(String path) {
        Path path_to_read = Paths.get(path);

        String contents = null;
        try {
            contents = Files.readString(path_to_read, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            System.out.println("Trouble with reading from file");
        }
        return contents;
    }

    public static String[] readLines(String filename) throws IOException {
        FileReader fileReader = new FileReader(filename);

        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<String> lines = new ArrayList<String>();
        String line = null;

        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
        }

        bufferedReader.close();

        return lines.toArray(new String[lines.size()]);
    }
}
