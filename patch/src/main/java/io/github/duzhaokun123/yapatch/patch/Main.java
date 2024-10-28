package io.github.duzhaokun123.yapatch.patch;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.github.duzhaokun123.yapatch.patch.utils.Logger;
import io.github.duzhaokun123.yapatch.patch.utils.StdLogger;

public class Main {
    public static class Patch {
        @Parameter(description = "apks")
        protected List<String> apkPaths = new ArrayList<>();

        @Parameter(names = {"-h", "--help"}, help = true, order = 0, description = "Print this message")
        protected boolean help = false;

        @Parameter(names = {"-o", "--output"}, description = "Output directory")
        protected String outputPath = ".";

        @Parameter(names = {"-k", "--keystore"}, arity = 4, description = "Set custom signature keystore. Followed by 4 arguments: keystore path, keystore password, keystore alias, keystore alias password", required = true)
        protected List<String> keystoreArgs = Arrays.asList(null, null, null, null);

        @Parameter(names = {"-m", "--modules"}, description = "Xposed modules package name to load")
        protected List<String> modules = new ArrayList<>();

        private final JCommander jCommander;
        protected final Logger logger;

        public Patch(Logger logger, String... args) {
            jCommander =JCommander.newBuilder()
                    .addObject(this)
                    .build();
            try {
                jCommander.parse(args);
            } catch (Exception e) {
                logger.error(e.getMessage());
                help = true;
            }

            if (apkPaths == null || apkPaths.isEmpty()) {
                logger.error("No apks specified");
                help = true;
            }

            this.logger = logger;
        }

        public void run() {
            logger.info("Running patch");
        }
    }

    public static void main(String[] args) {
        Patch patch = new PatchKt(StdLogger.INSTANCE, args);
        if (patch.help) {
            patch.jCommander.usage();
            return;
        }

        try {
            patch.run();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
}
