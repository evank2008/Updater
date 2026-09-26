import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

import org.kohsuke.github.GHAsset;
import org.kohsuke.github.GHRelease;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

public class Updater {
	
	//1: check github api for if your current version is out of date
	//2: if it is, download newest version(give popup first ofc)
	//3: once downloaded, make new process
	//4: quit main process
	//5: delete original file
	//6: place new file in proper directory
	//7: popup confirming update
	//8: end
	
	//really ought to store the jar file in the invoicer folder
	//maybe just change the script to find it
	String versionNum="3.1.1";
	String downloadLoc = FileSystemView.getFileSystemView().getDefaultDirectory().getPath()+"/Desktop/locus/";
	String tempName="Invoicah.jar";
	String finalName="InVoicer.jar";
	String repo = "evank2008/InVoicer";
	public static void main(String[] args) {
		
		new Updater();
	}
	public Updater() {
		System.out.println("sup");
		File location = new File(downloadLoc+tempName);
	//	System.out.println("Rename: "+location.renameTo(new File(downloadLoc+finalName)));
		System.out.println(location.getPath());
		try {
			GitHub gh = new GitHubBuilder().build();
			GHRelease release = gh.getRepository(repo).getLatestRelease();
			String s = release.getTagName();
			System.out.println(s);
			if(!s.equals(versionNum)) {
				if(JOptionPane.showConfirmDialog(null, "Version "+s+" has been released. Update?", "UpDater", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
					
					System.out.println("update");
					for(GHAsset ass: release.getAssets()) {
						if(ass.getName().equals("InVoicer.jar")) {
							String url = ass.getBrowserDownloadUrl();
							System.out.println(url);
							URL downloadUrl = URI.create(url).toURL();
					        InputStream in = downloadUrl.openStream(); 
					        Files.copy(in, location.toPath(), StandardCopyOption.REPLACE_EXISTING);
					        System.out.println("Rename: "+location.renameTo(new File(downloadLoc+finalName)));
						}
					}
					
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}