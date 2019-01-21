/*
    @author Peter
 */
package schoolcouncillogin;

public class SchoolCouncilLogin {
    
    public static String saveDirectory; //K:\save.txt
    public static String backupDirectory = "\\\\backups.txt";
    
    static startScreen startGUI = new startScreen();
    
    
    
    //stores up to 100 logins at once. 
    //First name, last name, email,Phone number
    
    public static void main(String[] args) {
        //<editor-fold defaultstate="collapsed" desc=" Just learned editor folds, cool ">
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(startScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(startScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(startScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(startScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                startGUI.setVisible(true);
            }
        });
        
        //create a file with the default file location
        
        
    }
    
}
