package inventory.component;

import java.io.File;

public class ReadReportThread implements Runnable {

    private File file;

    public ReadReportThread(File file) {
        this.file = file;
    }

    @Override
    public void run() {
//        FileParser parser = new FileParser();
//        parser.readReport(file);
    }
}
