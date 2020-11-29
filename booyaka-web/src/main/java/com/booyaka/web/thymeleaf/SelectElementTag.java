package com.booyaka.web.thymeleaf;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;
import org.unbescape.html.HtmlEscape;

/**
 * 下拉框
 **/

public class SelectElementTag extends AbstractElementTagProcessor {
	private static final String TAG_NAME = "select";
	private static final int PRECEDENCE = 1000;
	private JdbcTemplate jdbcTemplate;
	private SelectOptionService selectOptionService;

	public SelectElementTag(final String dialectPrefix, JdbcTemplate jdbcTemplate) {
		super(TemplateMode.HTML, // This processor will apply only to HTML mode
				dialectPrefix, // Prefix to be applied to name for matching
				TAG_NAME, // Tag name: match specifically this tag
				true, // Apply dialect prefix to tag name
				null, // No attribute name: will match by tag name
				false, // No prefix to be applied to attribute name
				PRECEDENCE); // Precedence (inside dialect's own precedence)
		this.jdbcTemplate = jdbcTemplate;
		this.selectOptionService = new SelectOptionServiceImpl();
	}

	@Override
	protected void doProcess(final ITemplateContext context, final IProcessableElementTag tag,
			final IElementTagStructureHandler structureHandler) {
		List<String> options = new ArrayList<>();
		String empty = tag.getAttributeValue("data-empty");
		String required = tag.getAttributeValue("data-required");

		String function = tag.getAttributeValue("data-function");
		if ("true".equals(empty)) {
			options.add("<option value = \"\">请选择</option>");
		}
		if ("true".equals(required)) {
		}
		final String param = tag.getAttributeValue("param");
		String classValue = tag.getAttributeValue("class");
		String id = tag.getAttributeValue("id");
		String name = tag.getAttributeValue("name");
		Boolean cacheable;
		if (StringUtils.isEmpty(tag.getAttributeValue("cacheable"))) {
			cacheable = true;
		} else {
			cacheable = Boolean.parseBoolean(tag.getAttributeValue("cacheable"));
		}

		List<Option> optionList = selectOptionService.queryOption(param, jdbcTemplate, cacheable);
		for (Option option : optionList) {
			StringBuilder optionSb = new StringBuilder();
			optionSb.append("<option value=\"");
			optionSb.append(option.getValue());
			optionSb.append("\">");
			optionSb.append(option.getText());
			optionSb.append("</option>");
			options.add(optionSb.toString());
		}

		final IModelFactory modelFactory = context.getModelFactory();

		final IModel model = modelFactory.createModel();
		model.add(modelFactory.createText("\n\t"));

		IProcessableElementTag openElementTag = modelFactory.createOpenElementTag("select", "class", classValue);
		if (!StringUtils.isEmpty(id)) {
			openElementTag = modelFactory.setAttribute(openElementTag, "id", id);
		}
		if (!StringUtils.isEmpty(name)) {
			openElementTag = modelFactory.setAttribute(openElementTag, "name", name);
		}
		if (!StringUtils.isEmpty(function)) {
			openElementTag = modelFactory.setAttribute(openElementTag, "onchange", function);
		}
		if ("true".equals(required)) {
			openElementTag = modelFactory.setAttribute(openElementTag, "required", "required");
		}

		model.add(openElementTag);
		model.add(modelFactory.createText("\n\t\t"));
		model.add(modelFactory.createText(HtmlEscape.unescapeHtml(String.join("\n\t\t", options))));
		model.add(modelFactory.createText("\n\t"));
		model.add(modelFactory.createCloseElementTag("select"));

		structureHandler.replaceWith(model, false);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		SelectElementTag that = (SelectElementTag) o;
		return Objects.equals(jdbcTemplate, that.jdbcTemplate)
				&& Objects.equals(selectOptionService, that.selectOptionService);
	}

	@Override
	public int hashCode() {
		return Objects.hash(jdbcTemplate, selectOptionService);
	}
}
