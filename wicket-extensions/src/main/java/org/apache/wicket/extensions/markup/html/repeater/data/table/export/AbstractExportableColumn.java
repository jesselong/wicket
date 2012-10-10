package org.apache.wicket.extensions.markup.html.repeater.data.table.export;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;

public abstract class AbstractExportableColumn<R, S, T>
    extends AbstractColumn<R, S>
    implements IExportableColumn<R, S, T>
{

    public AbstractExportableColumn(IModel<String> displayModel)
    {
        super(displayModel);
    }

    public AbstractExportableColumn(IModel<String> displayModel, S sortProperty)
    {
        super(displayModel, sortProperty);
    }
    
    protected abstract Component createDisplayComponent(String componentId, IModel<T> dataModel);

    @Override
    public void populateItem(Item<ICellPopulator<R>> cellItem, String componentId, IModel<R> rowModel)
    {
        cellItem.add(createDisplayComponent(componentId, getDataModel(rowModel)));
    }
}
