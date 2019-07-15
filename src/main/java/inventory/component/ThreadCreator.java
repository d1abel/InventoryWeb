package inventory.component;

import java.io.File;

public class ThreadCreator implements Runnable {

    private File file;

    public ThreadCreator(File file) {
        this.file = file;
    }

    @Override
    public void run() {
        FileParser parser = new FileParser();
        parser.read(file);
    }
}
