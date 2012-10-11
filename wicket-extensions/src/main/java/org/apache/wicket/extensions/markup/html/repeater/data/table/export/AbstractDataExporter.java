package org.apache.wicket.extensions.markup.html.repeater.data.table.export;

import org.apache.wicket.model.IModel;

public abstract class AbstractDataExporter
        implements IDataExporter
{
	private final IModel<String> dataFormatNameModel;
	private final String contentType;
	private final String fileNameExtension;

	public AbstractDataExporter(IModel<String> dataFormatNameModel, String contentType, String fileNameExtension)
	{
		this.dataFormatNameModel = dataFormatNameModel;
		this.contentType = contentType;
		this.fileNameExtension = fileNameExtension;
	}

	@Override
	public IModel<String> getDataFormatNameModel()
	{
		return dataFormatNameModel;
	}

	@Override
	public String getContentType()
	{
		return contentType;
	}

	@Override
	public String getFileNameExtension()
	{
		return fileNameExtension;
	}
}
