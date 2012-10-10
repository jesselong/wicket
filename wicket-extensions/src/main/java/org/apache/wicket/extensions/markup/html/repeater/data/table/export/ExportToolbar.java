/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.wicket.extensions.markup.html.repeater.data.table.export;

import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.ResourceLink;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.resource.ResourceStreamResource;
import org.apache.wicket.util.lang.Args;
import org.apache.wicket.util.resource.AbstractResourceStreamWriter;
import org.apache.wicket.util.resource.IResourceStream;

public class ExportToolbar
        extends AbstractToolbar
{
    private static final long serialVersionUID = 1L;
    private static final IModel<String> DEFAULT_MESSAGE_MODEL = new ResourceModel(
            "datatable.export-to");
    private static final IModel<String> DEFAULT_FILE_NAME_MODEL = new ResourceModel(
            "datatable.export-file-name");
    private final List<IDataExporter> dataExporters = new LinkedList<IDataExporter>();
    private final IModel<String> messageModel;
    private IModel<String> fileNameModel;

    /**
     * Constructor
     *
     * @param table data table this toolbar will be attached to
     */
    public ExportToolbar(final DataTable<?, ?> table)
    {
        this(table, DEFAULT_MESSAGE_MODEL, DEFAULT_FILE_NAME_MODEL);
    }

    public ExportToolbar(DataTable<?, ?> table, IModel<String> messageModel, IModel<String> fileNameModel)
    {
        super(table);
        this.messageModel = messageModel;
        this.fileNameModel = fileNameModel;
    }

    

    @Override
    protected void onInitialize()
    {
        super.onInitialize();

        WebMarkupContainer td = new WebMarkupContainer("td");
        add(td);

        td.add(AttributeModifier.replace("colspan", new AbstractReadOnlyModel<String>()
        {
            private static final long serialVersionUID = 1L;

            @Override
            public String getObject()
            {
                return String.valueOf(getTable().getColumns().size());
            }
        }));

        td.add(new Label("exportTo", messageModel));

        RepeatingView linkContainers = new RepeatingView("linkContainer");
        td.add(linkContainers);

        for (IDataExporter exporter : dataExporters){
            WebMarkupContainer span = new WebMarkupContainer(linkContainers.newChildId());
            linkContainers.add(span);

            span.add(createExportLink("exportLink", exporter));
        }
    }

    protected Component createExportLink(String componentId, final IDataExporter dataExporter)
    {
        return new ResourceLink<Void>(componentId, new ResourceStreamResource()
        {
            @Override
            protected IResourceStream getResourceStream()
            {
                return new DataExportResourceStreamWriter(dataExporter);
            }
        }
                .setFileName(fileNameModel.getObject() + "." + dataExporter.getFileNameExtension())
                );
    }

    @Override
    public boolean isVisible()
    {
        if (dataExporters.isEmpty()){
            return false;
        }
        
        if (getTable().getRowCount() == 0){
            return false;
        }
        
        for (IColumn<?, ?> col : getTable().getColumns()){
            if (col instanceof IExportableColumn){
                return true;
            }
        }
        
        return false;
    }

    public ExportToolbar addDataExporter(IDataExporter exporter)
    {
        Args.notNull(exporter, "exporter");
        dataExporters.add(exporter);
        return this;
    }

    public class DataExportResourceStreamWriter
            extends AbstractResourceStreamWriter
    {
        private final IDataExporter dataExporter;

        public DataExportResourceStreamWriter(IDataExporter dataExporter)
        {
            this.dataExporter = dataExporter;
        }

        @Override
        public void write(OutputStream output)
                throws IOException
        {
            exportData(getTable(), dataExporter, output);
        }

        @Override
        public String getContentType()
        {
            return dataExporter.getContentType();
        }

        private <T, S> void exportData(DataTable<T, S> dataTable, IDataExporter dataExporter, OutputStream outputStream)
                throws IOException
        {
            IDataProvider<T> dataProvider = dataTable.getDataProvider();
            List<IExportableColumn<T, ?, ?>> exportableColumns = new LinkedList<IExportableColumn<T, ?, ?>>();
            for (IColumn<T, S> col : dataTable.getColumns()){
                if (col instanceof IExportableColumn){
                    exportableColumns.add((IExportableColumn<T, ?, ?>)col);
                }
            }
            dataExporter.exportData(dataProvider, exportableColumns, outputStream);
        }
    }
}
