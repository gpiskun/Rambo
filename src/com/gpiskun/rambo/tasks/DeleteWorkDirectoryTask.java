package com.gpiskun.rambo.tasks;

import java.io.File;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchManager;

import com.gpiskun.rambo.preferences.RamboPreferenceField;

public class DeleteWorkDirectoryTask implements Runnable {
	
	private static final String WORKING_DIR = "org.eclipse.jdt.launching.WORKING_DIRECTORY";
	
	@Override
	public void run() {
		try {
			ILaunchConfiguration launchConfiguration = getLaunchConfiguration();
			String path = (String) launchConfiguration.getAttributes().get(WORKING_DIR);
			File directory = new File(path);
			directory.delete();
		} catch (CoreException e) {
			throw new RuntimeException(e);
		}
	}
	
	private ILaunchConfiguration getLaunchConfiguration() throws CoreException {
		ILaunchManager launchManager = DebugPlugin.getDefault().getLaunchManager();
		return launchManager.getLaunchConfiguration(RamboPreferenceField.RUN_CONFIG.getStringValue());
	}
}
