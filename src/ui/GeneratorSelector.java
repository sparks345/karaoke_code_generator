package ui;

import business.IGenerator;
import business.generator.ParcelGenerator;
import com.intellij.psi.PsiFile;

import javax.swing.*;
import java.awt.event.*;

public class GeneratorSelector extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JList list1;
    private JRadioButton parcelRadioButton;
    private JRadioButton dbCacheDataRadioButton;
    private JRadioButton reportRadioButton;

    private PsiFile mCurrentPsiFile;

    public GeneratorSelector() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        IGenerator generator = getSelectedGenerator();
        generator.run();
        dispose();
    }

    private IGenerator getSelectedGenerator() {
        IGenerator generator = null;
        if(parcelRadioButton.isSelected()) {
            generator = new ParcelGenerator(mCurrentPsiFile);
        }

        return generator;
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        GeneratorSelector dialog = new GeneratorSelector();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    public void setContextPsiFile(PsiFile currentPsiFile) {
        mCurrentPsiFile = currentPsiFile;
    }
}
