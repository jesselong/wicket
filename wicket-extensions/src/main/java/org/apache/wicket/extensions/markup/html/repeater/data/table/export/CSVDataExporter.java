package org.apache.wicket.extensions.markup.html.repeater.data.table.export;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;
import org.apache.wicket.Application;
import org.apache.wicket.Session;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.convert.IConverter;

public class CSVDataExporter
        extends AbstractDataExporter
{
    public CSVDataExporter()
    {
        super(Model.of("CSV"), "text/csv", "csv");
    }

    @Override
    public <R> void exportData(IDataProvider<R> dataProvider, List<IExportableColumn<R, ?, ?>> columns, OutputStream outputStream)
            throws IOException
    {
        PrintWriter out = new PrintWriter(new OutputStreamWriter(outputStream, Charset.forName("UTF-8")));
        try {
            boolean first = true;
            for (IExportableColumn<R, ?, ?> col : columns){
                if (first){
                    first = false;
                }else{
                    out.print(",");
                }
                out.print("\"");
                out.print(col.getDisplayModel().getObject().replace("\"", "\"\""));
                out.print("\"");
            }
            out.println();
            long numberOfRows = dataProvider.size();
            Iterator<? extends R> rowIterator = dataProvider.iterator(0, numberOfRows);
            while (rowIterator.hasNext()){
                R row = rowIterator.next();

                first = true;
                for (IExportableColumn<R, ?, ?> col : columns){
                    if (first){
                        first = false;
                    }else{
                        out.print(",");
                    }

                    Object o = col.getDataModel(dataProvider.model(row)).getObject();

                    if (o != null){
                        Class<?> c = o.getClass();

                        String s;

                        if (c == String.class){
                            s = o.toString();
                        }else{
                            IConverter converter = Application.get().getConverterLocator().getConverter(c);

                            s = converter.convertToString(o, Session.get().getLocale());
                        }

                        out.print("\"" + s.replace("\"", "\"\"") + "\"");
                    }

                    out.print("\"");
                }
            }
        }finally{
            out.close();
        }
    }
}
