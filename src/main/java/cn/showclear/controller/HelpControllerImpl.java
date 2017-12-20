package cn.showclear.controller;
import java.io.*;


public class HelpControllerImpl implements IController {

    @Override
    public void method() {
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;

        try {
//            inputStream = new FileInputStream(System.getProperty("user.dir") + File.separator + "doc" + File.separator + "readme.md");
            inputStream = this.getClass().getResourceAsStream("/readme.md");

            bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
            String line = bufferedReader.readLine();
            while (line != null) {
                System.out.println(line);
                line = bufferedReader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) inputStream.close();
                if (bufferedReader != null) bufferedReader.close();
            } catch (Exception ignored) {
            }
        }

    }
}
