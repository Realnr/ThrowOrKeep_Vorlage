package view;

import control.MainController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InteractionPanelHandler {

    private MainController controller;

    private JPanel mainPanel;
    private JButton nextCardButton;
    private JButton keepButton;
    private JButton throwButton;
    private JTextArea textArea1;
    private JTextPane textPane;

    public InteractionPanelHandler(MainController controller){
        this.controller = controller;
        createButtons();
    }

    public void createButtons(){
        nextCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int next=controller.showNextCard();
                if(next!=-1){
                    addToSysOutput(""+next);
                }else{
                    addToSysOutput("Keine Karten mehr");
                    endGame();
                }
            }
        });
        keepButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(controller.keep()) {
                    addToSysOutput("Karte behalten");
                    if(controller.cardStackEmpty()){
                        endGame();
                    }
                } else {
                    addToSysOutput("Keine Karte mehr vorhanden. Beende Spiel");
                    endGame();
                }
            }
        });
        throwButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(controller.throwCard()) {
                    addToSysOutput("Karte weggeworfen");
                    if(controller.cardStackEmpty()){
                        endGame();
                    }
                } else {
                    addToSysOutput("Keine Karte mehr vorhanden");
                }
            }
        });
    }

    public void endGame(){
        int count=controller.inspect();
        if(count>=0){
            addToSysOutput2("Du hast insgesamt "+count+" Karten behalten.");
        } else{
            addToSysOutput2("Du hast keinen gültigen Stapel gebaut.");
        }
    }

    public JPanel getPanel() {
        return mainPanel;
    }

    private void addToSysOutput(String text){
        textArea1.setText(text);
    }

    private void addToSysOutput2(String text){
        textPane.setText(text);
    }
}
