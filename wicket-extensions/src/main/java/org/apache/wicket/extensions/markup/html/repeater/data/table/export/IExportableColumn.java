package org.apache.wicket.extensions.markup.html.repeater.data.table.export;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.model.IModel;

public interface IExportableColumn<R, S, T>
    extends IColumn<R, S>
{
    IModel<T> getDataModel(IModel<R> rowModel);
    
    IModel<String> getDisplayModel();
}
