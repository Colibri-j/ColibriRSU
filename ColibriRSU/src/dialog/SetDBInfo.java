/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dialog;

import colibrirsu.syst.Ather;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author i++
 * (c)Colibri. 2021 year.
 */
public class SetDBInfo extends Framer{

    private static JDialog informationEntryForm;
    private static JTextArea inputField;

    public static void setInformation(final String dbName) {
        informationEntryForm = new JDialog();
        informationEntryForm.setSize(getSyze(25, 40));
        informationEntryForm.setLocation(NewDb.getPoint());
        Box workspace = Box.createVerticalBox();
            inputField = new JTextArea();
                inputField.setLineWrap(true);
            JPanel buttonBar = new JPanel();
                JButton ok = new JButton("гаразд");
                    ok.addActionListener((ActionEvent e) -> {
                        String[] enteredText = inputField.getText().split("\n");
                        String finalText = "<html>\n";
                        for(int i = 0; i < enteredText.length; i++){
                            finalText += "<p/>";
                            if(enteredText[i].length() > 40){
                                String[] termAttached = Ather.cut(enteredText[i], 40);
                                finalText += termAttached[0];
                                for(int a = 0; a < termAttached.length; a++){
                                    finalText += "\n<br>" + termAttached[a];
                                }
                            }
                            else{
                                finalText += enteredText[i];
                            }
                        }
                        finalText += "</html>";
                        activ.get(dbName).setDatabaseInformation(finalText);
                        activ.get(dbName).save();
                        openDBlist();
                        informationEntryForm.setVisible(false);
                    });
                JButton cancel = new JButton("відмінити");
                    cancel.addActionListener((ActionEvent e) -> {
                        informationEntryForm.setVisible(false);
                    });
            buttonBar.add(ok);
            buttonBar.add(cancel);
        workspace.add(inputField);
        workspace.add(buttonBar);
        JScrollPane sp = new JScrollPane(workspace, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            sp.setPreferredSize(OpenDb.getSecondSyze(95));
        informationEntryForm.getContentPane().add(sp);
        informationEntryForm.setVisible(true);
    }
    
}
