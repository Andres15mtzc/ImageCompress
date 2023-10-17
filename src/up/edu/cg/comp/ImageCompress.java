package up.edu.cg.comp;

import up.edu.cg.comp.tools.IOConsole;
import up.edu.cg.comp.tools.IOHandler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class ImageCompress {
    private BufferedImage image;
    private int height;
    private int width;

    /**
     * This method is to save the decompressed image
     * @param image The image that we create with the compressed file
     * @param filename name for your file
     * @param extension the image extension (bmp)
     */
    public static void saveImage(BufferedImage image, String filename, String extension) {
        File outputImage = new File(filename + "." + extension);
        try {
            ImageIO.write(image, extension, outputImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method create a compressed file and write the info on it
     * @param code an ArrayList of String with the rbg's int for the pixels
     * @param filename name for your file
     * @param extension the extension for your compressed file
     */
    public static void saveFile(ArrayList<String> code, String filename, String extension){
        File outputImage = new File(filename + "." + extension);
        try {
            outputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        PrintWriter printWriter = null;
        {
            try {
                printWriter = new PrintWriter(filename + "." + extension);
            } catch (FileNotFoundException e) {
                System.out.println("Unable to locate the fileName: " + e.getMessage());
            }
            for (int i=0; i<code.size(); i++) {
                Objects.requireNonNull(printWriter).println(code.get(i));
            }
            printWriter.close();
        }
    }

    public ImageCompress(){

    }

    /**
     * This method ask for a path to an image and validate it
     * It gets the info(rgb) of a part of the pixels and save it into a compressed file
     */
    public void compress(){
        IOHandler inputOutput = new IOConsole();
        BufferedImage image = inputOutput.getImage("Write the path to the image", "Please select a valid path");
        setImage(image);
        setHeight(image.getHeight());
        setWidth(image.getWidth());

        BufferedImage newImage = new BufferedImage(image.getWidth()/2, image.getHeight()/2, BufferedImage.TYPE_INT_RGB);
        ArrayList<String> code = new ArrayList<>();
        System.out.println("Compressing...");
        for (int y = 0; y < newImage.getHeight(); y++) {
            String line= "";
            for (int x = 0; x < newImage.getWidth(); x++) {
                line += (image.getRGB(2*x, 2*y)) + " ";
            }
            code.add(line);
        }
        saveFile(code, "compressImage", "uwu");
        System.out.println("Image compressed");

    }

    /**
     * This method ask for a path to a compressed file and validate it
     * Later it save an image with the info in the compressed file
     * Finally it expands the image to the original resolution and save it creating a new file
     */
    public void decompress(){
        IOHandler inputOutput = new IOConsole();
        File file = inputOutput.getFile("Write the path", "Please select a valid path", "uwu");
        ArrayList<String[]> data = new ArrayList<>();
        System.out.println("Decompressing...");
        try{
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String l;
            int i = 0;
            while((l=br.readLine()) != null){
                String[] dataLine = l.split("\\ ");
                data.add(dataLine);
                i++;
            }
        } catch (Exception e){
            System.out.println(e);
        }

        BufferedImage image = new BufferedImage(data.get(0).length, data.size(), BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < data.size(); y++) {
            for (int x = 0; x < data.get(0).length; x++) {
                image.setRGB(x, y, Integer.parseInt(data.get(y)[x]));
            }
        }

        setImage(image);
        setHeight(image.getHeight());
        setWidth(image.getWidth());

        BufferedImage newImage = new BufferedImage(image.getWidth()*2, image.getHeight()*2, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < newImage.getWidth(); x++) {
            for (int y = 0; y < newImage.getHeight(); y++) {
                newImage.setRGB(x, y, image.getRGB(x / 2, y / 2));
                if(x+1 < newImage.getWidth()){
                    newImage.setRGB(x+1, y, image.getRGB(x / 2, y / 2));
                }
                if(y+1 < newImage.getHeight()){
                    newImage.setRGB(x, y+1, image.getRGB(x / 2, y / 2));
                }
                if(x+1 < newImage.getWidth() && y+1 < newImage.getHeight()){
                    newImage.setRGB(x+1, y+1, image.getRGB(x / 2, y / 2));
                }
            }
        }
        saveImage(newImage, "decompressImage", "bmp");
        System.out.println("Image Decompressed");
    }

    public BufferedImage getImage() {
        return image;
    }
    private void setImage(BufferedImage image) {
        this.image = image;
    }
    public int getHeight() {
        return height;
    }
    private void setHeight(int height) {
        this.height = height;
    }
    public int getWidth() {
        return width;
    }
    private void setWidth(int width) {
        this.width = width;
    }

}
