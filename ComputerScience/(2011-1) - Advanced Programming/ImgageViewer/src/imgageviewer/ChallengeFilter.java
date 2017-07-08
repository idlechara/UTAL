/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package imgageviewer;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class ChallengeFilter extends FileFilter
{
    @Override
   public boolean accept (File file)
   {
      if (this.isGif(file) || this.isJpg(file) || this.isBmp(file) || this.isPng(file))
         return true;
      else
         return false;
   }
    @Override
   public String getDescription()
   {
      return ("Image filter");
   }
   
   private boolean isJpg(File file){
       if (file.getName().toLowerCase().endsWith(".jpg"))
           return true;
       else return false;
   }   
   private boolean isBmp(File file){
       if (file.getName().toLowerCase().endsWith(".bmp"))
           return true;
       else return false;
   }   
   private boolean isGif(File file){
       if (file.getName().toLowerCase().endsWith(".gif"))
           return true;
       else return false;
   }   
   private boolean isPng(File file){
       if (file.getName().toLowerCase().endsWith(".png"))
           return true;
       else return false;
   }
}