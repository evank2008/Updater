import java.io.File;

import javax.swing.JOptionPane;

public class Independent {
	
	public static void main(String[] args) {
		//0: absolute path of downloaded invoicah
		//1: absolute path of invoicer
		//2: version num
		if(args.length<3) {
			JOptionPane.showMessageDialog(null, "Must run Updater through InVoicer.");
			System.exit(0);
		}
		File location = new File(args[0]);
		String newNamePath = args[1];
		boolean work = location.renameTo(new File(newNamePath));
        System.out.println("Rename: "+work);
        if(work) {
        	JOptionPane.showMessageDialog(null, "InVoicer successfully updated to v"+args[2]+".");
        } else {
        	JOptionPane.showMessageDialog(null, "Error updating.");
        }
	}

	
}