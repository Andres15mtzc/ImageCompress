package up.edu.cg.comp;

import up.edu.cg.comp.tools.IOConsole;
import up.edu.cg.comp.tools.IOHandler;

public class Main {

    public static void main(String[] args) {
        IOHandler inputOutput = new IOConsole();
        inputOutput.showInfo("Welcome to the best image compressor!!!!!");
        ImageCompress image = new ImageCompress();
        int option = inputOutput.getInt("Select an option: \n1)Compress\n2)Decompress", "Please select a valid option");

        switch (option){
            case 1:
                image.compress();
                break;
            case 2:
                image.decompress();
                break;
        }
    }
}
