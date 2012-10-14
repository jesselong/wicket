package org.apache.wicket.extensions.markup.html.repeater.data.table.export;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.io.IClusterable;

/**
 * Exports data provided by a {@link IDataProvider} as described by {@link IExportableColumn}s. This interface is used by
 * {@link ExportToolbar} to provide the export functionality.
 *
 * @author Jesse Long
 * @see ExportToolbar
 * @see IExportableColumn
 */
public interface IDataExporter
	extends IClusterable
{
	/**
	 * Returns a model of the exported data format name. This should be something like "CSV" or "Excel" etc. The
	 * value of the model returned is displayed as the export type in the {@link ExportToolbar}.
	 * @return a model of the exported data format name.
	 */
	IModel<String> getDataFormatNameModel();

	/**
	 * Returns the MIME content type of the export data type. This could be something like "text/csv". This is used to provide
	 * the correct content type when downloading the exported data.
	 * @return the MIME content type of the export data type.
	 */
	String getContentType();

	/**
	 * Returns the file name extensions for the exported data, without the ".". For CSV, this should be "csv". For Excel
	 * exports, this should be "xls".
	 * @return the file name extensions for the exported data, without the ".".
	 */
	String getFileNameExtension();

	/**
	 * Exports the data provided by the {@link IDataProvider} to the {@link OutputStream}.
	 * @param <T> The type of each row of data provided by the {@link IDataProvider}.
	 * @param dataProvider The {@link IDataProvider} from which to retrieve the data.
	 * @param columns The {@link IExportableColumn} to use to describe the data.
	 * @param outputStream The {@link OutputStream} to which to write the exported data.
	 * @throws IOException If an error occurs.
	 */
	<T> void exportData(IDataProvider<T> dataProvider, List<IExportableColumn<T, ?, ?>> columns, OutputStream outputStream)
		throws IOException;
}
