import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import parcs.*;

public class Algo implements AM{


    public void run(AMInfo info) {

        String content = (String) info.parent.readObject();
        String[] positives = (String[]) info.parent.readObject();
        String[] negatives = (String[]) info.parent.readObject();
        String[] sports = (String[]) info.parent.readObject();
        String[] covid = (String[]) info.parent.readObject();
        String[] aviation = (String[]) info.parent.readObject();

        content = content.toLowerCase();
        content = content.replaceAll("\\p{Punct}", "");
        Pattern p = Pattern.compile("\\b(i|me|my|myself|we|our|ours|something|ourselves|you|your|yours|yourself|yourselves|he|him|his|himself|she|her|hers|herself|it|its|itself|they|them|their|theirs|themselves|what|which|who|whom|this|that|these|those|am|is|are|was|were|be|been|being|have|has|had|having|do|does|did|doing|a|an|the|and|but|if|or|because|as|until|while|of|at|by|for|with|about|against|between|into|through|during|before|after|above|below|to|from|up|down|in|out|on|off|over|under|again|further|then|once|here|there|when|where|why|how|all|any|both|each|few|more|most|other|some|such|no|nor|not|only|own|same|so|than|too|very|s|t|can|will|just|don|should|now)\\b\\s?");
        Matcher m = p.matcher(content);
        content = m.replaceAll("");

        int sport_words = 0;
        int cov_words = 0;
        int av_words = 0;
        int pos_words = 0;
        int neg_words = 0;

        String[] words = content.split(" |\\r\\n");
        for (String word : words) {
            if (Arrays.asList(sports).contains(word)) {
                sport_words++;
                continue;
            }

            if (Arrays.asList(covid).contains(word)) {
                cov_words++;
                continue;
            }

            if (Arrays.asList(aviation).contains(word)) {
                av_words++;
            }
        }

        for (String word : words) {

            if (Arrays.asList(positives).contains(word)) {
                pos_words++;
                continue;
            }

            if (Arrays.asList(negatives).contains(word)) {
                neg_words++;
            }
        }


        if (av_words == 0 && cov_words == 0 && sport_words == 0) {
            info.parent.write("None of suggested topics is topic of this article");
        } else if (av_words >= cov_words && av_words >= sport_words) {
            info.parent.write("Main topic of this article is aviation.");
        } else if (cov_words >= av_words && cov_words >= sport_words) {
            info.parent.write("Main topic of this article is COVID.");
        } else {
            info.parent.write("Main topic of this article is sport.");
        }


        if (pos_words > neg_words) {
            info.parent.write("Overall sentiment of article is positive");
        } else {
            info.parent.write("Overall sentiment of article is negative");
        }

    }
}
