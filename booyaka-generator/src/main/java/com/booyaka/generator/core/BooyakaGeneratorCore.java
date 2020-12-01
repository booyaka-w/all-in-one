package com.booyaka.generator.core;

import static org.mybatis.generator.internal.util.messages.Messages.getString;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.GeneratedKotlinFile;
import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.Plugin;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.api.ShellCallback;
import org.mybatis.generator.config.CommentGeneratorConfiguration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.JDBCConnectionConfiguration;
import org.mybatis.generator.config.JavaClientGeneratorConfiguration;
import org.mybatis.generator.config.JavaModelGeneratorConfiguration;
import org.mybatis.generator.config.JavaTypeResolverConfiguration;
import org.mybatis.generator.config.ModelType;
import org.mybatis.generator.config.PluginConfiguration;
import org.mybatis.generator.config.SqlMapGeneratorConfiguration;
import org.mybatis.generator.config.TableConfiguration;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.mybatis.generator.internal.NullProgressCallback;
import org.mybatis.generator.internal.PluginAggregator;
import org.mybatis.generator.plugins.ControllerPlugin;
import org.mybatis.generator.plugins.DefaultPlugin;
import org.mybatis.generator.plugins.ServicePlugin;
import org.mybatis.generator.plugins.ToStringPlugin;

import com.booyaka.generator.util.WriteFiles;

/**
 * 
 * @author edge_
 *
 */
public class BooyakaGeneratorCore {

	private Set<String> projects = new HashSet<>();

	private ShellCallback shellCallback = new DefaultShellCallback(true);

	private List<String> warnings = new ArrayList<>();

	private ProgressCallback callback = new NullProgressCallback();

	private Set<String> fullyQualifiedTableNames = null;

	private List<GeneratedJavaFile> generatedJavaFiles = new ArrayList<>();

	private List<GeneratedXmlFile> generatedXmlFiles = new ArrayList<>();

	private List<GeneratedKotlinFile> generatedKotlinFiles = new ArrayList<>();

	private static PluginAggregator pluginAggregator;

	private String targetProject = ".\\src\\main\\java";

	private String targetPackage = "com.booyaka.web.system";

	private String tableName;

	private String driverClass = "com.mysql.cj.jdbc.Driver";

	private String connectionURL;

	private String userId = "root";

	private String password = "root";

	/**
	 * 
	 */
	private Context context;

	public BooyakaGeneratorCore(String targetProject, String targetPackage, String tableName, String driverClass,
			String connectionURL, String userId, String password) {
		this.targetPackage = targetPackage;
		this.tableName = tableName;
		this.connectionURL = connectionURL;
		if (StringUtils.isNotBlank(driverClass)) {
			this.driverClass = driverClass;
		}
		if (StringUtils.isNotBlank(targetProject)) {
			this.targetProject = targetPackage;
		}
		if (StringUtils.isNotBlank(userId)) {
			this.userId = userId;
		}
		if (StringUtils.isNotBlank(password)) {
			this.password = password;
		}
	}

	public BooyakaGeneratorCore(String targetPackage, String tableName, String connectionURL) {
		this.targetPackage = targetPackage;
		this.tableName = tableName;
		this.connectionURL = connectionURL;
	}

	public Context getContext() {
		if (context == null) {
			context = new Context(ModelType.CONDITIONAL);
		}
		if (pluginAggregator == null) {
			pluginAggregator = new PluginAggregator();
		}
		context.setTargetRuntime("MyBatis3");

		context.setJavaTypeResolverConfiguration(getJavaTypeResolverConfiguration());

		/* jdcb */
		context.setJdbcConnectionConfiguration(getJdbcConnectionConfiguration());
		/* model */
		context.setJavaModelGeneratorConfiguration(getJavaModelGeneratorConfiguration());
		/* XML */
		context.setSqlMapGeneratorConfiguration(getSqlMapGeneratorConfiguration());
		/* mapper */
		context.setJavaClientGeneratorConfiguration(getJavaClientGeneratorConfiguration());
		context.setJava8Targeted(true);

		/* 添加插件 */
		Plugin defaultPlugin = new DefaultPlugin();
		defaultPlugin.setContext(context);
		pluginAggregator.addPlugin(defaultPlugin);
		PluginConfiguration defaultPluginConfiguration = new PluginConfiguration();
		defaultPluginConfiguration.setConfigurationType("org.mybatis.generator.plugins.DefaultPlugin");
		context.addPluginConfiguration(defaultPluginConfiguration);

		Plugin servicePlugin = new ServicePlugin();
		servicePlugin.setContext(context);
		pluginAggregator.addPlugin(servicePlugin);
		PluginConfiguration servicePluginConfiguration = new PluginConfiguration();
		servicePluginConfiguration.addProperty("servicePackage", targetPackage + ".service");
		servicePluginConfiguration.addProperty("serviceImplPackage", targetPackage + ".service.impl");
		servicePluginConfiguration.addProperty("targetProject", targetProject);
		servicePluginConfiguration.addProperty("targetPackage", targetPackage);
		servicePluginConfiguration.setConfigurationType("org.mybatis.generator.plugins.ServicePlugin");
		context.addPluginConfiguration(servicePluginConfiguration);

		Plugin controllerPlugin = new ControllerPlugin();
		servicePlugin.setContext(context);
		pluginAggregator.addPlugin(controllerPlugin);
		PluginConfiguration controllerPluginConfiguration = new PluginConfiguration();
		controllerPluginConfiguration.addProperty("servicePackage", targetPackage + ".service");
		controllerPluginConfiguration.addProperty("controllerPackage", targetPackage + ".controller");
		controllerPluginConfiguration.addProperty("targetProject", targetProject);
		controllerPluginConfiguration.addProperty("targetPackage", targetPackage);
		controllerPluginConfiguration.setConfigurationType("org.mybatis.generator.plugins.ControllerPlugin");
		context.addPluginConfiguration(controllerPluginConfiguration);

		Plugin toStringPlugin = new ToStringPlugin();
		toStringPlugin.setContext(context);
		pluginAggregator.addPlugin(toStringPlugin);
		PluginConfiguration toStringPluginConfiguration = new PluginConfiguration();
		toStringPluginConfiguration.setConfigurationType("org.mybatis.generator.plugins.ToStringPlugin");
		context.addPluginConfiguration(toStringPluginConfiguration);

		context.setCommentGeneratorConfiguration(getCommentGeneratorConfiguration());

		context.addTableConfiguration(getTableConfiguration());

		return context;
	}

	private JDBCConnectionConfiguration getJdbcConnectionConfiguration() {
		JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
		jdbcConnectionConfiguration.setConnectionURL(connectionURL);
		jdbcConnectionConfiguration.setDriverClass(driverClass);
		jdbcConnectionConfiguration.setPassword(password);
		jdbcConnectionConfiguration.setUserId(userId);
		return jdbcConnectionConfiguration;
	}

	private JavaModelGeneratorConfiguration getJavaModelGeneratorConfiguration() {
		JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = new JavaModelGeneratorConfiguration();
		javaModelGeneratorConfiguration.setTargetProject(targetProject);
		javaModelGeneratorConfiguration.setTargetPackage(targetPackage + ".model");
		return javaModelGeneratorConfiguration;
	}

	private SqlMapGeneratorConfiguration getSqlMapGeneratorConfiguration() {
		SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();
		sqlMapGeneratorConfiguration.setTargetProject(targetProject);
		sqlMapGeneratorConfiguration.setTargetPackage(targetPackage + ".mapper");
		return sqlMapGeneratorConfiguration;
	}

	private JavaClientGeneratorConfiguration getJavaClientGeneratorConfiguration() {
		JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = new JavaClientGeneratorConfiguration();
		javaClientGeneratorConfiguration.setTargetProject(targetProject);
		javaClientGeneratorConfiguration.setTargetPackage(targetPackage + ".dao");
		javaClientGeneratorConfiguration.setConfigurationType("XMLMAPPER");
		return javaClientGeneratorConfiguration;
	}

	public TableConfiguration getTableConfiguration() {
		TableConfiguration tableConfiguration = new TableConfiguration(new Context(null));
		// tableConfiguration.setSchema(SCHEMA);
		tableConfiguration.setTableName(tableName);
		tableConfiguration.setSelectByExampleStatementEnabled(false);
		tableConfiguration.setUpdateByExampleStatementEnabled(false);
		tableConfiguration.setDeleteByExampleStatementEnabled(false);
		tableConfiguration.setCountByExampleStatementEnabled(false);
		return tableConfiguration;
	}

	public CommentGeneratorConfiguration getCommentGeneratorConfiguration() {
		CommentGeneratorConfiguration commentGeneratorConfiguration = new CommentGeneratorConfiguration();
		commentGeneratorConfiguration.addProperty("suppressDate", "true");
		commentGeneratorConfiguration.addProperty("suppressAllComments", "false");
		return commentGeneratorConfiguration;
	}

	public JavaTypeResolverConfiguration getJavaTypeResolverConfiguration() {
		JavaTypeResolverConfiguration javaTypeResolverConfiguration = new JavaTypeResolverConfiguration();
		/*
		 * 默认 false false,把JDBC DECIMAL 和 NUMERIC 类型解析为 Integer true,把JDBC DECIMAL 和
		 * NUMERIC 类型解析为java.math.BigDecimal
		 */
		javaTypeResolverConfiguration.addProperty("forceBigDecimals", "true");
		return javaTypeResolverConfiguration;
	}

	public ProgressCallback getCallBack() throws InterruptedException {
		ProgressCallback callback = new NullProgressCallback();
		callback.generationStarted(0);
		callback.introspectionStarted(0);
		callback.startTask(getString("Progress.0"));
		callback.startTask(getString("Progress.1", "sys_user"));
		callback.checkCancel();
		return callback;
	}

	/**
	 * 
	 * @throws SQLException
	 * @throws InterruptedException
	 */
	public void introspectTables() throws SQLException, InterruptedException {
		context.introspectTables(callback, warnings, fullyQualifiedTableNames);
	}

	public void generateFiles() throws InterruptedException {
		context.generateFiles(callback, generatedJavaFiles, generatedXmlFiles, generatedKotlinFiles, warnings);
	}

	public void writeFiles() throws InterruptedException, IOException {
		callback.saveStarted(generatedXmlFiles.size() + generatedJavaFiles.size());
		for (GeneratedXmlFile gxf : generatedXmlFiles) {
			projects.add(gxf.getTargetProject());
			WriteFiles.writeGeneratedXmlFile(gxf, callback);
		}
		for (GeneratedJavaFile gjf : generatedJavaFiles) {
			projects.add(gjf.getTargetProject());
			WriteFiles.writeGeneratedJavaFile(gjf, callback);
		}
//		for (GeneratedKotlinFile gkf : generatedKotlinFiles) {
//			projects.add(gkf.getTargetProject());
//			WriteFiles.writeGeneratedKotlinFile(gkf, callback);
//		}
		for (String project : projects) {
			shellCallback.refreshProject(project);
		}
		callback.done();
	}

}
