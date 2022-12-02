package working.table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class TestWorkingTable {

	private static final String NAME_COLUMN = "Name";
	private static final String FAMILY_NAME_COLUMN = "Family name";
	private static final String[] PROPS = { NAME_COLUMN, FAMILY_NAME_COLUMN };

	public static void main(String[] args) {

		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new GridLayout());

		Table personsTable = new Table(shell, SWT.FULL_SELECTION | SWT.MULTI | SWT.V_SCROLL | SWT.BORDER);

		personsTable.setHeaderVisible(true);
		personsTable.setLinesVisible(true);
		final GridData gdclientsTable = new GridData(GridData.FILL, GridData.FILL, true, true);
		gdclientsTable.heightHint = 70;
		personsTable.setLayoutData(gdclientsTable);

		final TableColumn nameTableColumn = new TableColumn(personsTable, SWT.NONE);
		nameTableColumn.setWidth(400);
		nameTableColumn.setText(NAME_COLUMN);
		final TableColumn familyNameTableColumn = new TableColumn(personsTable, SWT.NONE);
		familyNameTableColumn.setWidth(400);
		familyNameTableColumn.setText(FAMILY_NAME_COLUMN);

		TableViewer clientsTableViewer = new TableViewer(personsTable);
		clientsTableViewer.setColumnProperties(PROPS);
		clientsTableViewer.setCellEditors(
				new CellEditor[] { new TextCellEditor(personsTable), new TextCellEditor(personsTable) });
		clientsTableViewer.setContentProvider(new ArrayContentProvider());
		clientsTableViewer.setLabelProvider(new ClientsLabelProvider());
		clientsTableViewer.setCellModifier(new ClientsCellModifier(clientsTableViewer));
		clientsTableViewer.setInput(Arrays.asList(new Person("Jacob", "Patriiuk")
				, new Person("Joan", "Patriiuk")
				));

		////////////////////////////
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();

	}

	final static class ClientsLabelProvider implements ITableLabelProvider {

		@Override
		public Image getColumnImage(final Object element, final int columnIndex) {
			return null;
		}

		@Override
		public String getColumnText(final Object element, final int columnIndex) {
			if (element instanceof Person) {
				final Person person = (Person) element;
				if (columnIndex == 0) {
					return person.getName();
				} else if (columnIndex == 1) {
					return person.getFamilyName();
				}
			}
			return null;
		}

		@Override
		public void addListener(final ILabelProviderListener listener) {
		}

		@Override
		public void dispose() {
		}

		@Override
		public boolean isLabelProperty(final Object element, final String property) {
			return false;
		}

		@Override
		public void removeListener(final ILabelProviderListener listener) {
		}
	}

	final static class ClientsCellModifier implements ICellModifier {

		private TableViewer viewer;

		public ClientsCellModifier(final TableViewer viewer) {
			this.viewer = viewer;
		}

		@Override
		public boolean canModify(final Object element, final String property) {
			return true;
		}

		@Override
		public Object getValue(final Object element, final String property) {
			if (element instanceof Person) {
				final Person person = (Person) element;
				if (NAME_COLUMN.equals(property)) {
					return person.getName();
				} else if (FAMILY_NAME_COLUMN.equals(property)) {
					return person.getFamilyName();
				}
			}
			return null;
		}

		@Override
		public void modify(final Object element, final String property, final Object value) {
			if (element instanceof TableItem && ((TableItem)element).getData() instanceof Person) {
				final Person person = (Person)((TableItem)element).getData();

				if (NAME_COLUMN.equals(property)) {
					if (value instanceof String) {
						final String newValue = ((String) value).trim();
						if (!newValue.isEmpty() && !Objects.equals(newValue, person.getName())) {
							person.setName(newValue);
							System.out.println("Name is modified!");
							viewer.refresh();
						}
					}
				}
			}
		}
	}

}
