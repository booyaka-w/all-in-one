package com.booyaka.generator;

import java.io.IOException;
import java.sql.SQLException;

import com.booyaka.generator.core.BooyakaGeneratorCore;
import com.booyaka.generator.util.WriteFiles;

public class Generator {
	public static void main(String[] args) throws SQLException, InterruptedException, IOException {

		String connectionURL = "jdbc:mysql://localhost:3306/booyaka?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8&useSSL=false&transformedBitIsBoolean=true&allowPublicKeyRetrieval=true";
		String targetPackage = "com.booyaka.web.system";
		String tableName = "sys_user_info";

		BooyakaGeneratorCore generator = new BooyakaGeneratorCore(targetPackage, tableName, connectionURL);
		generator.getContext();
		generator.introspectTables();
		generator.generateFiles();
		generator.writeFiles();
		WriteFiles.warning();
	}
}
