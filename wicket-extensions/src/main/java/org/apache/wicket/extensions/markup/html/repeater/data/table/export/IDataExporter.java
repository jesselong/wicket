package org.apache.wicket.extensions.markup.html.repeater.data.table.export;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.io.IClusterable;

public interface IDataExporter
    extends IClusterable
{
    IModel<String> getDataFormatNameModel();
    
    <R> void exportData(IDataProvider<R> dataProvider, List<IExportableColumn<R, ?, ?>> columns, OutputStream outputStream)
            throws IOException;
    
    String getContentType();
    
    String getFileNameExtension();
}
