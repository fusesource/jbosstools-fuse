package org.jboss.tools.fuse.transformation.extensions;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.jboss.tools.fuse.transformation.editor.Activator;
import org.jboss.tools.fuse.transformation.editor.internal.MappingDetailViewer;

public class PreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {


    public PreferencePage() {
        super(GRID);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
     */
    @Override
    public void init(IWorkbench workbench) {
        setPreferenceStore(Activator.preferences());
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.preference.FieldEditorPreferencePage#createFieldEditors()
     */
    @Override
    protected void createFieldEditors() {
        addField(new ColorFieldEditor(MappingDetailViewer.TRANSFORMATION_FOREGROUND_PREFERENCE,
                                      "Transformation foreground in Details view",
                                      getFieldEditorParent()));
        addField(new ColorFieldEditor(MappingDetailViewer.TRANSFORMATION_BACKGROUND_PREFERENCE,
                                      "Transformation background in Details view",
                                      getFieldEditorParent()));
        addField(new BooleanFieldEditor(MappingDetailViewer.TRANSFORMATION_USER_FRIENDLY_FORMAT_PREFERENCE,
                                        "Show user-friendly formatting of transformations in Details view",
                                        getFieldEditorParent()));
    }
}
