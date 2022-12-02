package tnp.testtool.content.provider;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableColumn;

public class MyClass {

    private static final String LINE = "123456789A123456789B123456789C123456789D123456789E123456789F123456789G123456789H123456789I123456789J"// 100
            + "123456789K123456789L123456789M123456789N123456789O123456789P123456789Q123456789R123456789S123456789T" // 200
            + "123456789U123456789V123456789W123456789X123456789Y123456789Z" // 260
            + " a lot mor text";

    public MyClass(Shell shell){
        createControl(shell);
    }

    private void createControl(Composite parent){
        parent.setLayout(new GridLayout(1, true));

        TableViewer viewer = new TableViewer(parent, SWT.FULL_SELECTION | SWT.BORDER | SWT.V_SCROLL |SWT.H_SCROLL);
        viewer.getTable().setHeaderVisible(true);
        viewer.getTable().setLinesVisible(true);
        viewer.setContentProvider(ArrayContentProvider.getInstance());
        viewer.setLabelProvider(new LabelProvider());

        createColumn(viewer);

        viewer.setInput(new String[] { LINE });
        for(TableColumn col : viewer.getTable().getColumns()){
            col.pack();
        }

        GridDataFactory.fillDefaults().grab(true, true).applyTo(viewer.getControl());
    }

    private void createColumn(TableViewer viewer) {
        TableViewerColumn column1 = new TableViewerColumn(viewer, SWT.NONE);
        column1.getColumn().setText("ColumnLabelProvider");
        column1.setLabelProvider(new ColumnLabelProvider(){
            @Override
            public void update(ViewerCell cell) {
                cell.setText(cell.getElement().toString());
                super.update(cell);
            }
        });
        TableViewerColumn column2 = new TableViewerColumn(viewer, SWT.NONE);
        column2.getColumn().setText("StyledCellLabelProvider");
        column2.setLabelProvider(new StyledCellLabelProvider() {
            @Override
            public void update(ViewerCell cell) {
                cell.setText(cell.getElement().toString());
                super.update(cell);
            }
        }); 
    }

    public static void main(String[] args) {
        Display display = new Display();
        Shell shell = new Shell(display);
        new MyClass(shell); 
        shell.open();
        while(!shell.isDisposed()){
            if(!display.readAndDispatch()){
                display.sleep();
            }
        }
    }
}