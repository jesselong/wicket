package org.apache.wicket.extensions.markup.html.repeater.data.table.export;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.model.IModel;

/**
 * An {@link IColumn} that can be exported. {@link IExportableColumn}s provide methods for retrieving the data
 * displayed by this column in a row. This data is used by {@link IDataExporter}s to export data.
 * @author Jesse Long
 * @param <T> The type of each row in the table.
 * @param <S> The type of the sort property of the table.
 * @param <D> The type of the data displayed by this column.
 * @see IDataExporter
 * @see ExportToolbar
 */
public interface IExportableColumn<T, S, D>
    extends IColumn<T, S>
{
	/**
	 * Returns an {@link IModel} of the data displayed by this column for the {@code rowModel} provided.
	 * @param rowModel An {@link IModel} of the row data.
	 * @return an {@link IModel} of the data displayed by this column for the {@code rowModel} provided.
	 */
	IModel<D> getDataModel(IModel<T> rowModel);

	/**
	 * Returns a model of the column header. The content of this model is used as a heading for the column
	 * when it is exported.
	 * @return a model of the column header.
	 */
	IModel<String> getDisplayModel();
}
