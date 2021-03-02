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
            String path = "input1.txt";
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
