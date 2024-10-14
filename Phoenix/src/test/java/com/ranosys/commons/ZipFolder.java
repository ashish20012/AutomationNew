package com.ranosys.commons;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * ZipFolder Class is responsible for all Zip Related functions
 * @author Arif Khan
 * @version 1.0
 * @since 23-07-2024
 */
public class ZipFolder {

	// Get Console Logger instance to log messages in console
	private static final Logger LOGGER = Logger.getLogger(ZipFolder.class.getName());

	private List<String> filesListToBeZipped;
	private String sourceFolder;
	private String zipFileLocation;

	private FileOutputStream fos = null;
	private ZipOutputStream zos = null;

	/**
	 * ZipFolder() is a constructor which is initialize ZipOutPutstream
	 * 
	 * @author Arif Khan
	 * @version 1.0
	 * @since 23-07-2024
	 * 
	 * @param sourceFolder
	 * @param zipFile
	 */
	public ZipFolder(String sourceFolder, String zipFileLocation) {

		filesListToBeZipped = new ArrayList<String>();
		this.sourceFolder = sourceFolder;
		this.zipFileLocation = zipFileLocation;

		// Create Zip File Output Stream
		try {
			fos = new FileOutputStream(this.zipFileLocation);
			zos = new ZipOutputStream(fos);
		} catch (FileNotFoundException e) {
			LOGGER.info(zipFileLocation + " Not Found " + e.getMessage());
		}
	}

	/**
	 * zipFile() Method will iterate all filesListToBeZipped array items and create
	 * a Zip file at zipFileLocation
	 * 
	 * @author Arif Khan
	 * @version 1.0
	 * @since 23-07-2024
	 * 
	 */
	public void zipFile() {

		// add all files available in sourceFolder into filesListToBeZipped array
		// variable
		File sourceFolderFile = new File(sourceFolder);
		generateFilesListTobeZipped(sourceFolderFile);

		byte[] buffer = new byte[1024];
		String sourceFolderName = sourceFolderFile.getName();

		FileInputStream fileInputStream = null;

		try {
			for (String fileToBeZipped : filesListToBeZipped) {

				LOGGER.info("File Added : " + fileToBeZipped);
				ZipEntry zipEntry = new ZipEntry(PhonixUtility.createPath(sourceFolderName, fileToBeZipped));
				zos.putNextEntry(zipEntry);

				try {
					fileInputStream = new FileInputStream(PhonixUtility.createPath(sourceFolder, fileToBeZipped));
					int length;
					while ((length = fileInputStream.read(buffer)) > 0) {
						zos.write(buffer, 0, length);
					}
				} finally {
					fileInputStream.close();
				}
			}

			zos.closeEntry();
			LOGGER.info("Folder successfully compressed");
		} catch (IOException e) {
			LOGGER.info("Issue in Input/Output Stream " + e.getMessage());
		} finally {
			try {
				zos.close();
			} catch (IOException e) {
				LOGGER.info("Issue wile closing zipoutput stream " + e.getMessage());
			}
		}

	}

	/**
	 * generateFilesListTobeZipped() method will add all files available in
	 * sourceFolder into filesListToBeZipped array variable
	 * 
	 * @author Arif Khan
	 * @version 1.0
	 * @since 23-07-2024
	 * 
	 * @param sourceFolderFile
	 */
	private void generateFilesListTobeZipped(File sourceFolderFile) {
		// If sourceFolderFile is a Actual Single File then add into filesListToBeZipped
		// array variable
		if (sourceFolderFile.isFile()) {
			filesListToBeZipped.add(generateZipEntry(sourceFolderFile.toString()));
		}

		// If sourceFolderFile is a Directory then recursively call
		// generateFilesListTobeZipped to add files in filesListToBeZipped array
		// variable
		if (sourceFolderFile.isDirectory()) {
			String[] sourceFiles = sourceFolderFile.list();
			for (String sourceFile : sourceFiles) {
				generateFilesListTobeZipped(new File(sourceFolderFile, sourceFile));
			}
		}
	}

	/**
	 * generateZipEntry() Method is use to generate Zip entry
	 * 
	 * @author Arif Khan
	 * @version 1.0
	 * @since 23-07-2024
	 * 
	 * @param sourceFolderFile
	 * @return String
	 */
	private String generateZipEntry(String sourceFolderFile) {
		return sourceFolderFile.substring(this.sourceFolder.length() + 1, sourceFolderFile.length());
	}

}
