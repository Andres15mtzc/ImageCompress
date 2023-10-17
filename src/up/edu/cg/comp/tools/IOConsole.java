package up.edu.cg.comp.tools;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;

/**
 * This class allows the user to get data from the console and validates it
 * It uses a scanner that connects with System.in
 */
public class IOConsole extends IOHandler {

    private Scanner scanner;

    /**
     * The constructor initializes the scanner without user input
     */
    public IOConsole() {
        setScanner(new Scanner(System.in));
    }

    /**
     * @return scanner
     */
    public Scanner getScanner() {
        return scanner;
    }

    /**
     *
     * @param scanner
     */
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void showInfo(String info) {
        System.out.println(info);
    }

    /**
     * This method obtains a validated int from the user
     * @param info that will be shown to ask for the input
     * @param notValidInput text that will be shown when the input is not valid
     * @return int validated
     */
    @Override
    public int getInt(String info, String notValidInput) {
        boolean validate = false;
        int data = 0;
        do {
            showInfo(info);
            try {
                String userInput = getScanner().nextLine();
                data = Integer.parseInt(userInput);
                validate = true;
            } catch (Exception e) {
                showInfo(notValidInput);
            }
        } while (!validate);
        return data;
    }

    /**
     * This method obtains a validated file from the user
     * @param info that will be shown to ask for the input
     * @param notValidInput text that will be shown when the input is not valid
     * @param type the type that will have the compressed file to be decompressed
     * @return File validated
     */
    @Override
    public File getFile(String info, String notValidInput, String type) {
        boolean validate = false;
        File file = null;
        do {
            showInfo(info);
            try {
                String userInput = getScanner().nextLine();
                file = new File(userInput);
                if(file.exists()){
                    String[] path = userInput.replace('.', ' ').split(" ");
                    String newPath = path[path.length-1];
                    if (newPath.equals(type)){
                        validate = true;
                    }else{
                        showInfo(notValidInput);
                    }
                }else{
                    showInfo(notValidInput);
                }
            } catch (Exception e) {
                showInfo(notValidInput);
            }
        } while (!validate);
        return file;
    }
    /**
     * This method obtains a validated image from the user
     * @param info that will be shown to ask for the input
     * @param notValidInput text that will be shown when the input is not valid
     * @return BufferedImage validated
     */
    @Override
    public BufferedImage getImage(String info, String notValidInput) {
        boolean validate = false;
        BufferedImage image = null;
        do {
            try {
                IOHandler inputOutput = new IOConsole();
                File file = inputOutput.getFile("Write the path to the image", "Please select a valid path", "bmp");
                image = ImageIO.read(file);
                if(image==null){
                    validate = false;
                    showInfo(notValidInput);
                }
                else{
                    validate = true;
                }
            } catch (Exception e) {
                showInfo(notValidInput);
            }
        } while (!validate);
        return image;
    }
}
