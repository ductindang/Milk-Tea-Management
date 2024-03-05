/**/
package DEV;

import java.io.*;
import java.util.logging.*;

public class WorkWithFile {

    String urlFile;

    public WorkWithFile(String urlFile) {
        this.urlFile = urlFile;
    }

    public void write(String s) {
        try (DataOutputStream os = new DataOutputStream(new FileOutputStream(this.urlFile, false))) {
            os.writeUTF(s);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(WorkWithFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WorkWithFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String read() {
        String result = "";
        try (DataInputStream os = new DataInputStream(new FileInputStream(this.urlFile))) {
            result += os.readUTF();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(WorkWithFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WorkWithFile.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

}
