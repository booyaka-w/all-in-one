package com.booyaka.generator.util;

import static org.mybatis.generator.internal.util.messages.Messages.getString;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.GeneratedKotlinFile;
import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.api.ShellCallback;
import org.mybatis.generator.config.MergeConstants;
import org.mybatis.generator.exception.ShellException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.mybatis.generator.internal.XmlFileMergerJaxp;

/**
 * 
 * @author edge_
 *
 */
public class WriteFiles {

	private static ShellCallback shellCallback = new DefaultShellCallback(true);

	private static List<String> warnings = new ArrayList<String>();

	public static void writeGeneratedXmlFile(GeneratedXmlFile gxf, ProgressCallback callback)
			throws InterruptedException, IOException {
		File targetFile;
		String source;
		try {
			File directory = shellCallback.getDirectory(gxf.getTargetProject(), gxf.getTargetPackage());
			targetFile = new File(directory, gxf.getFileName());
			if (targetFile.exists()) {
				if (gxf.isMergeable()) {
					source = XmlFileMergerJaxp.getMergedSource(gxf, targetFile);
				} else if (shellCallback.isOverwriteEnabled()) {
					source = gxf.getFormattedContent();
					warnings.add(getString("Warning.11", targetFile.getAbsolutePath()));
				} else {
					source = gxf.getFormattedContent();
					targetFile = getUniqueFileName(directory, gxf.getFileName());
					warnings.add(getString("Warning.2", targetFile.getAbsolutePath()));
				}
			} else {
				source = gxf.getFormattedContent();
			}
			callback.checkCancel();
			callback.startTask(getString("Progress.15", targetFile.getName()));
			writeFile(targetFile, source, "UTF-8");
		} catch (ShellException e) {
			warnings.add(e.getMessage());
		}
	}

	public static void writeGeneratedJavaFile(GeneratedJavaFile gjf, ProgressCallback callback)
			throws InterruptedException, IOException {
		File targetFile;
		String source;
		try {
			File directory = shellCallback.getDirectory(gjf.getTargetProject(), gjf.getTargetPackage());
			targetFile = new File(directory, gjf.getFileName());
			if (targetFile.exists()) {
				if (shellCallback.isMergeSupported()) {
					source = shellCallback.mergeJavaFile(gjf.getFormattedContent(), targetFile,
							MergeConstants.getOldElementTags(), gjf.getFileEncoding());
				} else if (shellCallback.isOverwriteEnabled()) {
					source = gjf.getFormattedContent();
					warnings.add(getString("Warning.11", targetFile.getAbsolutePath()));
				} else {
					source = gjf.getFormattedContent();
					targetFile = getUniqueFileName(directory, gjf.getFileName());
					warnings.add(getString("Warning.2", targetFile.getAbsolutePath()));
				}
			} else {
				source = gjf.getFormattedContent();
			}

			callback.checkCancel();
			callback.startTask(getString("Progress.15", targetFile.getName()));
			writeFile(targetFile, source, gjf.getFileEncoding());
		} catch (ShellException e) {
			warnings.add(e.getMessage());
		}
	}

	public static void writeGeneratedKotlinFile(GeneratedKotlinFile gkf, ProgressCallback callback)
			throws InterruptedException, IOException {
		File targetFile;
		String source;
		try {
			File directory = shellCallback.getDirectory(gkf.getTargetProject(), gkf.getTargetPackage());
			targetFile = new File(directory, gkf.getFileName());
			if (targetFile.exists()) {
				if (shellCallback.isOverwriteEnabled()) {
					source = gkf.getFormattedContent();
					warnings.add(getString("Warning.11", targetFile.getAbsolutePath()));
				} else {
					source = gkf.getFormattedContent();
					targetFile = getUniqueFileName(directory, gkf.getFileName());
					warnings.add(getString("Warning.2", targetFile.getAbsolutePath()));
				}
			} else {
				source = gkf.getFormattedContent();
			}

			callback.checkCancel();
			callback.startTask(getString("Progress.15", targetFile.getName()));
			writeFile(targetFile, source, gkf.getFileEncoding());
		} catch (ShellException e) {
			warnings.add(e.getMessage());
		}
	}

	private static File getUniqueFileName(File directory, String fileName) {
		File answer = null;
		// try up to 1000 times to generate a unique file name
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < 1000; i++) {
			sb.setLength(0);
			sb.append(fileName);
			sb.append('.');
			sb.append(i);
			File testFile = new File(directory, sb.toString());
			if (!testFile.exists()) {
				answer = testFile;
				break;
			}
		}
		if (answer == null) {
			throw new RuntimeException(getString("RuntimeError.3", directory.getAbsolutePath()));
		}
		return answer;
	}

	private static void writeFile(File file, String content, String fileEncoding) throws IOException {
		FileOutputStream fos = new FileOutputStream(file, false);
		OutputStreamWriter osw;
		if (fileEncoding == null) {
			osw = new OutputStreamWriter(fos);
		} else {
			osw = new OutputStreamWriter(fos, fileEncoding);
		}
		try (BufferedWriter bw = new BufferedWriter(osw)) {
			bw.write(content);
		}
	}

	public static void warning() {
		for (String warning : warnings) {
			System.out.println(warning);
		}
	}
}
