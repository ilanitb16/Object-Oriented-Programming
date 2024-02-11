import java.io.File;
import java.io.IOException;

    /**
     * FindHypernyms is a class which finds hypernyms in a collection of
     * files.
     */
    public class DiscoverHypernyms {

        /**
         * @param args - first argument - file path. second argument - lemma.
         * @throws IOException exp.
         */
        public static void main(String[] args) throws IOException {
            CreateHypernyms base = new CreateHypernyms();
            String filePath = args[0];
            String lemma = args[1];
            File file = new File(filePath);

            File[] files = file.listFiles();
            if (files == null) {
                return;
            }
            for (final File fileEntry : files) {
                FileReader reader = new FileReader(fileEntry.getAbsolutePath(),
                        base.getHypernymMap());
                reader.readerBuffer();
            }
            base.findHypernymsByLemma(lemma);
        }
    }
