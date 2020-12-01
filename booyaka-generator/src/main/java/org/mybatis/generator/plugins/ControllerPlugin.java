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
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;

/**
 * TODO Controller插件
 * 
 * @author booyaka
 * @date 2019年12月23日 上午10:33:14
 */
public class ControllerPlugin extends PluginAdapter {

	private FullyQualifiedJavaType model;
	private FullyQualifiedJavaType service;
	private FullyQualifiedJavaType controller;

	private FullyQualifiedJavaType List = new FullyQualifiedJavaType("java.util.List");
	private FullyQualifiedJavaType AjaxJson = new FullyQualifiedJavaType("com.booyaka.commons.AjaxJson");
	private FullyQualifiedJavaType JSON = new FullyQualifiedJavaType("com.alibaba.fastjson.JSON");
	private FullyQualifiedJavaType PageModel = new FullyQualifiedJavaType("com.booyaka.commons.PageModel");
	private FullyQualifiedJavaType PageInfo = new FullyQualifiedJavaType("com.github.pagehelper.PageInfo");
	private FullyQualifiedJavaType Autowired = new FullyQualifiedJavaType(
			"org.springframework.beans.factory.annotation.Autowired");
	private FullyQualifiedJavaType Controller = new FullyQualifiedJavaType("org.springframework.stereotype.Controller");
	private FullyQualifiedJavaType RequestMapping = new FullyQualifiedJavaType(
			"org.springframework.web.bind.annotation.RequestMapping");
	private FullyQualifiedJavaType ResponseBody = new FullyQualifiedJavaType(
			"org.springframework.web.bind.annotation.ResponseBody");

	private String servicePackage;
	private String controllerPackage;
	private String targetProject;
	private String targetPackage;

	private Parameter parameter;
	private Parameter primaryKey;

	@Override
	public boolean validate(List<String> warnings) {
		this.servicePackage = properties.getProperty("servicePackage");
		this.controllerPackage = properties.getProperty("controllerPackage");
		this.targetProject = properties.getProperty("targetProject");
		this.targetPackage = properties.getProperty("targetPackage");
		return true;
	}

	@Override
	public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
		List<GeneratedJavaFile> files = new ArrayList<GeneratedJavaFile>();
		parameter = new Parameter(new FullyQualifiedJavaType(getType(introspectedTable.getBaseRecordType())),
				fristLower(introspectedTable.getBaseRecordType()));
		primaryKey = new Parameter(introspectedTable.getPrimaryKeyColumns().get(0).getFullyQualifiedJavaType(),
				introspectedTable.getPrimaryKeyColumns().get(0).getJavaProperty());
		String table = introspectedTable.getBaseRecordType();
		model = new FullyQualifiedJavaType(table);
		String tableName = table.replaceAll(this.targetPackage + ".model.", "");
		controller = new FullyQualifiedJavaType(controllerPackage + "." + tableName + "Controller");
		service = new FullyQualifiedJavaType(servicePackage + "." + tableName + "Service");
		TopLevelClass topLevelClass = new TopLevelClass(controller);
		addController(topLevelClass, introspectedTable, tableName, files);
		return files;
	}

	private void addController(TopLevelClass topLevelClass, IntrospectedTable introspectedTable, String tableName,
			List<GeneratedJavaFile> files) {
		topLevelClass.setVisibility(JavaVisibility.PUBLIC);

		topLevelClass.addJavaDocLine("/**");
		topLevelClass.addJavaDocLine(" * TODO " + introspectedTable.getRemarks() + "Controller");
		topLevelClass.addJavaDocLine(" *");
		topLevelClass.addJavaDocLine(" * @author booyaka");
		topLevelClass.addJavaDocLine(" * @date " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		topLevelClass.addJavaDocLine(" */");

		topLevelClass.addAnnotation("@Controller");
		topLevelClass.addImportedType(Controller);
		topLevelClass.addImportedType(Autowired);
		topLevelClass.addImportedType(PageInfo);
		topLevelClass.addImportedType(model);
		topLevelClass.addImportedType(AjaxJson);
		topLevelClass.addImportedType(List);
		topLevelClass.addImportedType(JSON);
		topLevelClass.addImportedType(PageModel);
		topLevelClass.addImportedType(ResponseBody);
		topLevelClass.addImportedType(RequestMapping);
		addField(topLevelClass, tableName);
		topLevelClass.addMethod(insert("insert", introspectedTable, tableName));
		topLevelClass.addMethod(deleteByPrimaryKey("deleteByPrimaryKey", introspectedTable, tableName));
		topLevelClass.addMethod(updateByPrimaryKey("updateByPrimaryKey", introspectedTable, tableName));
		topLevelClass.addMethod(queryByPrimaryKey("queryByPrimaryKey", introspectedTable, tableName));
		topLevelClass.addMethod(queryForList("queryForList", introspectedTable, tableName));
		topLevelClass.addMethod(queryForPage("queryForPage", introspectedTable, tableName));
		GeneratedJavaFile file = new GeneratedJavaFile(topLevelClass, targetProject, context.getJavaFormatter());
		files.add(file);
	}

	private void addField(TopLevelClass topLevelClass, String tableName) {
		Field field = new Field(toLowerCase(service.getShortName()), service);
		topLevelClass.addImportedType(service);
		field.setVisibility(JavaVisibility.PRIVATE);
		field.addAnnotation("@Autowired");
		topLevelClass.addField(field);
	}

	protected Method insert(String methodName, IntrospectedTable introspectedTable, String tableName) {
		Method method = new Method(methodName);
		method.addAnnotation("@RequestMapping()");
		method.addAnnotation("@ResponseBody");
		method.setReturnType(AjaxJson);
		method.addParameter(parameter);
		method.setVisibility(JavaVisibility.PUBLIC);
		method.addException(new FullyQualifiedJavaType("Exception"));

		method.addBodyLine("AjaxJson ajaxJson = new AjaxJson();");
		method.addBodyLine("try {");
		method.addBodyLine(toLowerCase(service.getShortName()) + ".insertSelective(" + parameter.getName() + ");");
		method.addBodyLine("} catch (Exception e) {");
		method.addBodyLine("ajaxJson.setSuccess(false);");
		method.addBodyLine("ajaxJson.setMsg(\"操作失败！\");");
		method.addBodyLine("e.printStackTrace();");
		method.addBodyLine("}");
		method.addBodyLine("return ajaxJson;");

		method.addJavaDocLine("/**");
		method.addJavaDocLine(" * TODO 添加");
		method.addJavaDocLine(" *  	");
		method.addJavaDocLine(" * @param " + parameter.getType());
		method.addJavaDocLine(" * @return " + AjaxJson.getShortName());
		method.addJavaDocLine(" * @throws Exception");
		method.addJavaDocLine(" */");
		return method;
	}

	private Method deleteByPrimaryKey(String methodName, IntrospectedTable introspectedTable, String tableName) {
		Method method = new Method(methodName);
		method.addAnnotation("@RequestMapping()");
		method.addAnnotation("@ResponseBody");
		method.setReturnType(AjaxJson);
		method.addParameter(primaryKey);
		method.setVisibility(JavaVisibility.PUBLIC);
		method.addException(new FullyQualifiedJavaType("Exception"));

		method.addBodyLine("AjaxJson ajaxJson = new AjaxJson();");
		method.addBodyLine("try {");
		method.addBodyLine(toLowerCase(service.getShortName()) + ".deleteByPrimaryKey(" + primaryKey.getName() + ");");
		method.addBodyLine("} catch (Exception e) {");
		method.addBodyLine("ajaxJson.setSuccess(false);");
		method.addBodyLine("ajaxJson.setMsg(\"操作失败！\");");
		method.addBodyLine("e.printStackTrace();");
		method.addBodyLine("}");
		method.addBodyLine("return ajaxJson;");

		method.addJavaDocLine("/**");
		method.addJavaDocLine(" * TODO 根据ID删除");
		method.addJavaDocLine(" *  	");
		method.addJavaDocLine(" * @param " + primaryKey.getName());
		method.addJavaDocLine(" * @return " + AjaxJson.getShortName());
		method.addJavaDocLine(" * @throws Exception");
		method.addJavaDocLine(" */");
		return method;
	}

	private Method updateByPrimaryKey(String methodName, IntrospectedTable introspectedTable, String tableName) {
		Method method = new Method(methodName);
		method.addAnnotation("@RequestMapping()");
		method.addAnnotation("@ResponseBody");
		method.setReturnType(AjaxJson);
		method.addParameter(parameter);
		method.setVisibility(JavaVisibility.PUBLIC);
		method.addException(new FullyQualifiedJavaType("Exception"));

		method.addBodyLine("AjaxJson ajaxJson = new AjaxJson();");
		method.addBodyLine("try {");
		method.addBodyLine(
				toLowerCase(service.getShortName()) + ".updateByPrimaryKeySelective(" + parameter.getName() + ");");
		method.addBodyLine("} catch (Exception e) {");
		method.addBodyLine("ajaxJson.setSuccess(false);");
		method.addBodyLine("ajaxJson.setMsg(\"操作失败！\");");
		method.addBodyLine("e.printStackTrace();");
		method.addBodyLine("}");
		method.addBodyLine("return ajaxJson;");

		method.addJavaDocLine("/**");
		method.addJavaDocLine(" * TODO 根据ID更新不为空的属性");
		method.addJavaDocLine(" *  	");
		method.addJavaDocLine(" * @param " + parameter.getType());
		method.addJavaDocLine(" * @return " + AjaxJson.getShortName());
		method.addJavaDocLine(" * @throws Exception");
		method.addJavaDocLine(" */");
		return method;
	}

	private Method queryByPrimaryKey(String methodName, IntrospectedTable introspectedTable, String tableName) {
		Method method = new Method(methodName);
		method.addAnnotation("@RequestMapping()");
		method.setReturnType(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));
		method.addParameter(primaryKey);
		method.setVisibility(JavaVisibility.PUBLIC);

		method.addBodyLine(
				"return " + toLowerCase(service.getShortName()) + ".selectByPrimaryKey(" + primaryKey.getName() + ");");

		method.addJavaDocLine("/**");
		method.addJavaDocLine(" * TODO 根据主键查询");
		method.addJavaDocLine(" *  	");
		method.addJavaDocLine(" * @param " + primaryKey.getName());
		method.addJavaDocLine(" * @return " + parameter.getType());
		method.addJavaDocLine(" */");
		return method;
	}

	private Method queryForList(String methodName, IntrospectedTable introspectedTable, String tableName) {
		Method method = new Method(methodName);
		method.addAnnotation("@RequestMapping()");
		method.setReturnType(new FullyQualifiedJavaType("List<" + model + ">"));
		method.addParameter(parameter);
		method.setVisibility(JavaVisibility.PUBLIC);

		method.addBodyLine(
				"return " + toLowerCase(service.getShortName()) + ".selectSelective(" + parameter.getName() + ");");

		method.addJavaDocLine("/**");
		method.addJavaDocLine(" * TODO 查询列表");
		method.addJavaDocLine(" *  	");
		method.addJavaDocLine(" * @param " + parameter.getType());
		method.addJavaDocLine(" * @return " + method.getReturnType().get());
		method.addJavaDocLine(" */");
		return method;
	}

	private Method queryForPage(String methodName, IntrospectedTable introspectedTable, String tableName) {
		Method method = new Method(methodName);
		method.addAnnotation("@RequestMapping()");
		method.setReturnType(FullyQualifiedJavaType.getStringInstance());
		method.addParameter(parameter);
		method.setVisibility(JavaVisibility.PUBLIC);

		method.addBodyLine("PageInfo<" + parameter.getType() + "> pageInfo = " + toLowerCase(service.getShortName())
				+ ".selectSelectiveForPage(" + parameter.getName() + ");");
		method.addBodyLine(
				"PageModel<" + parameter.getType() + "> pageModel = new PageModel<" + parameter.getType() + ">();");
		method.addBodyLine("pageModel.setRows(pageInfo.getList());");
		method.addBodyLine("pageModel.setTotal(pageInfo.getTotal());");
		method.addBodyLine("return JSON.toJSONString(pageModel);");

		method.addJavaDocLine("/**");
		method.addJavaDocLine(" * TODO 分页查询");
		method.addJavaDocLine(" *  	");
		method.addJavaDocLine(" * @param " + parameter.getType());
		method.addJavaDocLine(" * @return " + method.getReturnType().get());
		method.addJavaDocLine(" */");
		return method;
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
