package it.unibo.mvc;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {
    private static final int MIN = 0;
    private static final int MAX = 100;
    private static final int ATTEMPTS = 10;

    private static final String CONFIG_FILE = "config.yml";
    private static final String LOG = "output.log";

    private final DrawNumber model;
    private final List<DrawNumberView> views;

    /**
     * @param configFileName 
     * @param views the views to attach
     */
    public DrawNumberApp(final String configFileName, final DrawNumberView... views) {
        /*
         * Side-effect proof
         */
        Configuration conf = new Configuration.Builder()
        .setMin(MIN)
        .setMax(MAX)
        .setAttempts(ATTEMPTS).build();
        this.views = Arrays.asList(Arrays.copyOf(views, views.length));
        for (final DrawNumberView view : views) {
            view.setObserver(this);
            view.start();
        }
        try {
            final YamlReader yamlReader = new YamlReader(configFileName);
            conf = new Configuration.Builder()
                .setMin(yamlReader.getParameter("minimum"))
                .setMax(yamlReader.getParameter("maximum"))
                .setAttempts(yamlReader.getParameter("attempts")).build();
        } catch (IOException e) {
            displayError("Error while reading config file.");
        } finally {
            this.model = new DrawNumberImpl(conf);
        }
    }

    private void displayError(final String err) {
        for (final DrawNumberView view: views) {
            view.displayError(err);
        }
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            for (final DrawNumberView view : views) {
                view.result(result);
            }
        } catch (IllegalArgumentException e) {
            for (final DrawNumberView view : views) {
                view.numberIncorrect();
            }
        }
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public void quit() {
        /*
         * A bit harsh. A good application should configure the graphics to exit by
         * natural termination when closing is hit. To do things more cleanly, attention
         * should be paid to alive threads, as the application would continue to persist
         * until the last thread terminates.
         */
        System.exit(0);
    }

    /**
     * @param args
     *             ignored
     * @throws FileNotFoundException
     */
    public static void main(final String... args) throws FileNotFoundException {
        new DrawNumberApp(CONFIG_FILE,
        new DrawNumberViewImpl(),
        new DrawNumberViewImpl(),
        new PrintStreamView(LOG),
        new PrintStreamView(System.out));
    }

}
