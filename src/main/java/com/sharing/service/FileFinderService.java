package com.sharing.service;

import java.io.File;
import java.util.Set;
import java.util.TreeSet;

import com.sharing.exception.DirectoryDoesNotExistsException;
import com.sharing.util.SystemConstants;

public class FileFinderService {

	public Set<File> getFilesAndDirectory(String baseLocation) throws DirectoryDoesNotExistsException {
		Set<File> setOfFiles = null;
		if(baseLocation==null){
			baseLocation=SystemConstants.fileBasePath;
		}
		File directoryToScan = new File(baseLocation);
		if (directoryToScan == null || !directoryToScan.exists()) {
			throw new DirectoryDoesNotExistsException(
					"Unable to find any directory on specified path : " + SystemConstants.fileBasePath);
		} else {
			setOfFiles = fetchFiles(directoryToScan, false);
		}
		return setOfFiles;
	}

	public Set<File> getAllFiles() throws Exception {
		Set<File> setOfFiles = null;

		File directoryToScan = new File(SystemConstants.fileBasePath);
		if (directoryToScan == null || !directoryToScan.exists() || !directoryToScan.isDirectory()) {
			throw new DirectoryDoesNotExistsException(
					"Unable to find any directory on specified path : " + SystemConstants.fileBasePath);
		} else {
			setOfFiles = fetchFiles(directoryToScan, true);
		}

		return setOfFiles;
	}

	private Set<File> fetchFiles(File directory, boolean scanDirectory) {
		Set<File> filesToReturn = new TreeSet<>();
		if (directory.isFile()) {
			filesToReturn.add(directory);
		} else if (directory.isDirectory()) {
			for (File currentFile : directory.listFiles()) {
				if (currentFile.isFile()) {
					filesToReturn.add(currentFile);
				} else {
					if (scanDirectory) {
						filesToReturn.addAll(fetchFiles(currentFile, scanDirectory));
					} else {
						filesToReturn.add(currentFile);
					}
				}
			}
		}
		return filesToReturn;
	}
}
