package org.mybatis.generator.plugins;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;

/**
 * 
 * TODO Service插件
 * 
 * @author booyaka
 * @date 2019年12月23日 上午10:33:48
 */
public class ServicePlugin extends PluginAdapter {

	private FullyQualifiedJavaType model;
	private FullyQualifiedJavaType dao;
	private FullyQualifiedJavaType service;
	private FullyQualifiedJavaType serviceImpl;

	private FullyQualifiedJavaType list;
	private FullyQualifiedJavaType pageInfo;
	private FullyQualifiedJavaType pageHelper;

	private FullyQualifiedJavaType autowiredAnnotation = new FullyQualifiedJavaType(
			"org.springframework.beans.factory.annotation.Autowired");
	private FullyQualifiedJavaType serviceAnnotation = new FullyQualifiedJavaType(
			"org.springframework.stereotype.Service");
	private FullyQualifiedJavaType transactionalAnnotation = new FullyQualifiedJavaType(
			"org.springframework.transaction.annotation.Transactional");

	private String servicePackage;
	private String serviceImplPackage;
	private String targetProject;
	private String targetPackage;

	@Override
	public boolean validate(List<String> warnings) {

		this.servicePackage = properties.getProperty("servicePackage");
		this.serviceImplPackage = properties.getProperty("serviceImplPackage");
		this.targetProject = properties.getProperty("targetProject");
		this.targetPackage = properties.getProperty("targetPackage");

		return true;
	}

	@Override
	public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
		List<GeneratedJavaFile> files = new ArrayList<GeneratedJavaFile>();
		String table = introspectedTable.getBaseRecordType();
		model = new FullyQualifiedJavaType(table);
		String tableName = table.replaceAll(this.targetPackage + ".model.", "");
		list = new FullyQualifiedJavaType("java.util.List");
		pageInfo = new FullyQualifiedJavaType("com.github.pagehelper.PageInfo");
		pageHelper = new FullyQualifiedJavaType("com.github.pagehelper.PageHelper");
		dao = new FullyQualifiedJavaType(introspectedTable.getMyBatis3JavaMapperType());
		service = new FullyQualifiedJavaType(servicePackage + "." + tableName + "Service");
		serviceImpl = new FullyQualifiedJavaType(serviceImplPackage + "." + tableName + "ServiceImpl");

		Interface interfaze = new Interface(service);
		TopLevelClass topLevelClass = new TopLevelClass(serviceImpl);
		addImport(interfaze, topLevelClass);
		addService(interfaze, introspectedTable, tableName, files);
		addserviceImpl(topLevelClass, introspectedTable, tableName, files);
		return files;
	}

	protected void addService(Interface interfaze, IntrospectedTable introspectedTable, String tableName,
			List<GeneratedJavaFile> files) {
		interfaze.setVisibility(JavaVisibility.PUBLIC);
		interfaze.addJavaDocLine("/**");
		interfaze.addJavaDocLine(" * TODO " + introspectedTable.getRemarks() + "Service");
		interfaze.addJavaDocLine(" *");
		interfaze.addJavaDocLine(" * @author booyaka");
		interfaze.addJavaDocLine(" * @date " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		interfaze.addJavaDocLine(" */");

		Method method = insertSelective("insertSelective", introspectedTable, tableName);
		interfaze.addMethod(method);
		method = deleteByPrimaryKey("deleteByPrimaryKey", introspectedTable, tableName);
		interfaze.addMethod(method);
		method = updateSelectiveByPrimaryKey("updateSelectiveByPrimaryKey", introspectedTable, tableName);
		interfaze.addMethod(method);
		method = queryByPrimaryKey("queryByPrimaryKey", introspectedTable, tableName);
		interfaze.addMethod(method);
		method = querySelective("querySelective", introspectedTable, tableName);
		interfaze.addMethod(method);
		method = querySelectiveForPage("querySelectiveForPage", introspectedTable, tableName);
		interfaze.addMethod(method);
		GeneratedJavaFile file = new GeneratedJavaFile(interfaze, targetProject, context.getJavaFormatter());
		files.add(file);
	}

	protected Method insertSelective(String methodName, IntrospectedTable introspectedTable, String tableName) {
		Method method = new Method(methodName);
		method.setReturnType(FullyQualifiedJavaType.getIntInstance());
		method.addParameter(new Parameter(new FullyQualifiedJavaType(getType(introspectedTable.getBaseRecordType())),
				fristLower(introspectedTable.getBaseRecordType())));
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setAbstract(true);
		method.addException(new FullyQualifiedJavaType("Exception"));
		method.addJavaDocLine("/**");
		method.addJavaDocLine(" * TODO 添加" + method.getParameters().get(0).getType());
		method.addJavaDocLine(" *  	");
		method.addJavaDocLine(" * @param " + method.getParameters().get(0).getName());
		method.addJavaDocLine(" * @return " + method.getReturnType().get());
		method.addJavaDocLine(" * @throws Exception");
		method.addJavaDocLine(" */");
		return method;
	}

	protected Method deleteByPrimaryKey(String methodName, IntrospectedTable introspectedTable, String tableName) {
		Method method = new Method(methodName);
		method.setReturnType(FullyQualifiedJavaType.getIntInstance());
		method.addParameter(new Parameter(introspectedTable.getPrimaryKeyColumns().get(0).getFullyQualifiedJavaType(),
				introspectedTable.getPrimaryKeyColumns().get(0).getJavaProperty()));
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setAbstract(true);
		method.addException(new FullyQualifiedJavaType("Exception"));
		method.addJavaDocLine("/**");
		method.addJavaDocLine(
				" * TODO 根据主键删除" + new FullyQualifiedJavaType(getType(introspectedTable.getBaseRecordType())));
		method.addJavaDocLine(" *  	");
		method.addJavaDocLine(" * @param " + method.getParameters().get(0).getName());
		method.addJavaDocLine(" * @return " + method.getReturnType().get());
		method.addJavaDocLine(" * @throws Exception");
		method.addJavaDocLine(" */");
		return method;
	}

	protected Method updateSelectiveByPrimaryKey(String methodName, IntrospectedTable introspectedTable,
			String tableName) {
		Method method = new Method(methodName);
		method.setReturnType(FullyQualifiedJavaType.getIntInstance());
		method.addParameter(new Parameter(new FullyQualifiedJavaType(getType(introspectedTable.getBaseRecordType())),
				fristLower(introspectedTable.getBaseRecordType())));
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setAbstract(true);
		method.addException(new FullyQualifiedJavaType("Exception"));
		method.addJavaDocLine("/**");
		method.addJavaDocLine(" * TODO 根据对象属性更新" + method.getParameters().get(0).getType());
		method.addJavaDocLine(" *  	");
		method.addJavaDocLine(" * @param " + method.getParameters().get(0).getName());
		method.addJavaDocLine(" * @return " + method.getReturnType().get());
		method.addJavaDocLine(" * @throws Exception");
		method.addJavaDocLine(" */");
		return method;
	}

	protected Method queryByPrimaryKey(String methodName, IntrospectedTable introspectedTable, String tableName) {
		Method method = new Method(methodName);
		method.setReturnType(new FullyQualifiedJavaType(getType(introspectedTable.getBaseRecordType())));
		method.addParameter(new Parameter(introspectedTable.getPrimaryKeyColumns().get(0).getFullyQualifiedJavaType(),
				introspectedTable.getPrimaryKeyColumns().get(0).getJavaProperty()));
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setAbstract(true);
		method.addJavaDocLine("/**");
		method.addJavaDocLine(
				" * TODO 根据主键查询" + new FullyQualifiedJavaType(getType(introspectedTable.getBaseRecordType())));
		method.addJavaDocLine(" *  	");
		method.addJavaDocLine(" * @param " + method.getParameters().get(0).getName());
		method.addJavaDocLine(" * @return " + method.getReturnType().get());
		method.addJavaDocLine(" */");
		return method;
	}

	protected Method querySelective(String methodName, IntrospectedTable introspectedTable, String tableName) {
		Method method = new Method(methodName);
		method.setReturnType(
				new FullyQualifiedJavaType("List<" + getType(introspectedTable.getBaseRecordType()) + ">"));
		method.addParameter(new Parameter(new FullyQualifiedJavaType(getType(introspectedTable.getBaseRecordType())),
				fristLower(introspectedTable.getBaseRecordType())));
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setAbstract(true);
		method.addJavaDocLine("/**");
		method.addJavaDocLine(" * TODO 根据对象查询" + method.getParameters().get(0).getType());
		method.addJavaDocLine(" *  	");
		method.addJavaDocLine(" * @param " + method.getParameters().get(0).getName());
		method.addJavaDocLine(" * @return " + method.getReturnType().get());
		method.addJavaDocLine(" */");
		return method;
	}

	protected Method querySelectiveForPage(String methodName, IntrospectedTable introspectedTable, String tableName) {
		Method method = new Method(methodName);
		method.setReturnType(
				new FullyQualifiedJavaType("PageInfo<" + getType(introspectedTable.getBaseRecordType()) + ">"));
		method.addParameter(new Parameter(new FullyQualifiedJavaType(getType(introspectedTable.getBaseRecordType())),
				fristLower(introspectedTable.getBaseRecordType())));
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setAbstract(true);
		method.addJavaDocLine("/**");
		method.addJavaDocLine(" * TODO 根据对象查询" + method.getParameters().get(0).getType());
		method.addJavaDocLine(" *  	");
		method.addJavaDocLine(" * @param " + method.getParameters().get(0).getName());
		method.addJavaDocLine(" * @return " + method.getReturnType().get());
		method.addJavaDocLine(" */");
		return method;
	}

	protected void addserviceImpl(TopLevelClass topLevelClass, IntrospectedTable introspectedTable, String tableName,
			List<GeneratedJavaFile> files) {
		topLevelClass.setVisibility(JavaVisibility.PUBLIC);

		topLevelClass.addJavaDocLine("/**");
		topLevelClass.addJavaDocLine(" * TODO " + introspectedTable.getRemarks() + "ServiceImpl");
		topLevelClass.addJavaDocLine(" *");
		topLevelClass.addJavaDocLine(" * @author booyaka");
		topLevelClass.addJavaDocLine(" * @date " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		topLevelClass.addJavaDocLine(" */");

		topLevelClass.addSuperInterface(service);
		topLevelClass.addAnnotation("@Service");
		topLevelClass.addImportedType(serviceAnnotation);
		topLevelClass.addAnnotation("@Transactional(rollbackFor = Exception.class)");
		topLevelClass.addImportedType(transactionalAnnotation);
		topLevelClass.addImportedType(pageInfo);
		topLevelClass.addImportedType(pageHelper);
		addField(topLevelClass, tableName);

		topLevelClass.addMethod(insertSelectiveImpl("insertSelective", introspectedTable, tableName));
		topLevelClass.addMethod(deleteByPrimaryKeyImpl("deleteByPrimaryKey", introspectedTable, tableName));
		topLevelClass.addMethod(
				updateSelectiveByPrimaryKeyImpl("updateSelectiveByPrimaryKey", introspectedTable, tableName));
		topLevelClass.addMethod(queryByPrimaryKeyImpl("queryByPrimaryKey", introspectedTable, tableName));
		topLevelClass.addMethod(querySelectiveImpl("querySelective", introspectedTable, tableName));
		topLevelClass.addMethod(querySelectiveForPageImpl("querySelectiveForPage", introspectedTable, tableName));
		GeneratedJavaFile file = new GeneratedJavaFile(topLevelClass, targetProject, context.getJavaFormatter());
		files.add(file);
	}

	/**
	 * 属性
	 */
	protected void addField(TopLevelClass topLevelClass, String tableName) {
		Field field = new Field(toLowerCase(dao.getShortName()), dao);
		topLevelClass.addImportedType(dao);
		field.setVisibility(JavaVisibility.PRIVATE);
		field.addAnnotation("@Autowired");
		topLevelClass.addField(field);
	}

	protected Method insertSelectiveImpl(String methodName, IntrospectedTable introspectedTable, String tableName) {
		Method method = new Method(methodName);
		method.addAnnotation("@Override");
		method.setReturnType(FullyQualifiedJavaType.getIntInstance());
		method.setVisibility(JavaVisibility.PUBLIC);
		method.addParameter(new Parameter(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()),
				fristLower(introspectedTable.getBaseRecordType())));
		StringBuilder sb = new StringBuilder();
		sb.append("return ");
		sb.append(toLowerCase(dao.getShortName()) + ".");
		sb.append(methodName);
		sb.append("(");
		sb.append(fristLower(introspectedTable.getBaseRecordType()).toString());
		sb.append(");");
		method.addBodyLine(sb.toString());
		method.addException(new FullyQualifiedJavaType("Exception"));
		return method;
	}

	protected Method deleteByPrimaryKeyImpl(String methodName, IntrospectedTable introspectedTable, String tableName) {
		Method method = new Method(methodName);
		method.addAnnotation("@Override");
		method.setReturnType(FullyQualifiedJavaType.getIntInstance());
		method.setVisibility(JavaVisibility.PUBLIC);
		method.addParameter(new Parameter(introspectedTable.getPrimaryKeyColumns().get(0).getFullyQualifiedJavaType(),
				introspectedTable.getPrimaryKeyColumns().get(0).getJavaProperty()));
		StringBuilder sb = new StringBuilder();
		sb.append("return ");
		sb.append(toLowerCase(dao.getShortName()) + ".");
		sb.append(methodName);
		sb.append("(");
		sb.append(introspectedTable.getPrimaryKeyColumns().get(0).getJavaProperty().toString());
		sb.append(");");
		method.addBodyLine(sb.toString());
		method.addException(new FullyQualifiedJavaType("Exception"));
		return method;
	}

	protected Method updateSelectiveByPrimaryKeyImpl(String methodName, IntrospectedTable introspectedTable,
			String tableName) {
		Method method = new Method(methodName);
		method.addAnnotation("@Override");
		method.setReturnType(FullyQualifiedJavaType.getIntInstance());
		method.setVisibility(JavaVisibility.PUBLIC);
		method.addParameter(new Parameter(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()),
				fristLower(introspectedTable.getBaseRecordType())));
		StringBuilder sb = new StringBuilder();
		sb.append("return ");
		sb.append(toLowerCase(dao.getShortName()) + ".");
		sb.append(methodName);
		sb.append("(");
		sb.append(fristLower(introspectedTable.getBaseRecordType()).toString());
		sb.append(");");
		method.addBodyLine(sb.toString());
		method.addException(new FullyQualifiedJavaType("Exception"));
		return method;
	}

	protected Method queryByPrimaryKeyImpl(String methodName, IntrospectedTable introspectedTable, String tableName) {
		Method method = new Method(methodName);
		method.addAnnotation("@Override");
		method.setReturnType(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));
		method.setVisibility(JavaVisibility.PUBLIC);
		method.addParameter(new Parameter(introspectedTable.getPrimaryKeyColumns().get(0).getFullyQualifiedJavaType(),
				introspectedTable.getPrimaryKeyColumns().get(0).getJavaProperty()));
		StringBuilder sb = new StringBuilder();
		sb.append("return ");
		sb.append(toLowerCase(dao.getShortName()) + ".");
		sb.append(methodName);
		sb.append("(");
		sb.append(introspectedTable.getPrimaryKeyColumns().get(0).getJavaProperty().toString());
		sb.append(");");
		method.addBodyLine(sb.toString());
		return method;
	}

	protected Method querySelectiveImpl(String methodName, IntrospectedTable introspectedTable, String tableName) {
		Method method = new Method(methodName);
		method.addAnnotation("@Override");
		method.setReturnType(new FullyQualifiedJavaType("List<" + introspectedTable.getBaseRecordType() + ">"));
		method.setVisibility(JavaVisibility.PUBLIC);
		method.addParameter(new Parameter(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()),
				fristLower(introspectedTable.getBaseRecordType())));
		StringBuilder sb = new StringBuilder();
		sb.append("return ");
		sb.append(toLowerCase(dao.getShortName()) + ".");
		sb.append(methodName);
		sb.append("(");
		sb.append(fristLower(introspectedTable.getBaseRecordType()));
		sb.append(");");
		method.addBodyLine(sb.toString());
		return method;
	}

	protected Method querySelectiveForPageImpl(String methodName, IntrospectedTable introspectedTable,
			String tableName) {
		Method method = new Method(methodName);
		method.addAnnotation("@Override");
		method.setReturnType(new FullyQualifiedJavaType("PageInfo<" + introspectedTable.getBaseRecordType() + ">"));
		method.setVisibility(JavaVisibility.PUBLIC);
		method.addParameter(new Parameter(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()),
				fristLower(introspectedTable.getBaseRecordType())));
		StringBuilder sb1 = new StringBuilder();
		sb1.append("PageHelper.startPage(" + fristLower(introspectedTable.getBaseRecordType()) + ".getPageNum(),"
				+ fristLower(introspectedTable.getBaseRecordType()) + ".getPageSize(),"
				+ fristLower(introspectedTable.getBaseRecordType()) + ".getOrderBy());");
		method.addBodyLine(sb1.toString());
		StringBuilder sb2 = new StringBuilder();
		sb2.append(
				"List<" + getType(introspectedTable.getBaseRecordType()) + "> list =" + toLowerCase(dao.getShortName())
						+ ".selectSelective(" + fristLower(introspectedTable.getBaseRecordType()) + ");");
		method.addBodyLine(sb2.toString());
		StringBuilder sb3 = new StringBuilder();
		sb3.append("return new PageInfo<" + getType(introspectedTable.getBaseRecordType()) + ">(list);");
		method.addBodyLine(sb3.toString());
		return method;
	}

	private void addImport(Interface interfaces, TopLevelClass topLevelClass) {

		interfaces.addImportedType(list);
		interfaces.addImportedType(model);
		interfaces.addImportedType(pageInfo);

		topLevelClass.addImportedType(list);
		topLevelClass.addImportedType(autowiredAnnotation);
		topLevelClass.addImportedType(serviceAnnotation);
		topLevelClass.addImportedType(dao);
		topLevelClass.addImportedType(model);
		topLevelClass.addImportedType(service);
	}

	protected String toLowerCase(String tableName) {
		StringBuilder sb = new StringBuilder(tableName);
		sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
		return sb.toString();
	}

	protected String toUpperCase(String tableName) {
		StringBuilder sb = new StringBuilder(tableName);
		sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
		return sb.toString();
	}

	private String fristLower(String str) {
		str = str.substring(str.lastIndexOf(".") + 1, str.length());
		char[] chars = str.toCharArray();
		chars[0] += 32;
		return String.valueOf(chars);
	}

	private String getType(String baseRecordType) {
		baseRecordType = baseRecordType.replaceAll(this.targetPackage + ".model.", "");
		return baseRecordType;
	}
}
