package control;

import view.ViewWindow;
import Model.Card;
import Model.Stack;

public class MainController {

    private Stack<Card> stackOrigin;  //Ursprünglicher Stapel mit zufälligen Zahlen/Karten
    private Stack<Card> stackKeep;    //Stapel der Karten, der behalten wird

    public MainController() {
        new ViewWindow(this);

        startProgram();
    }

    /**
     * Erstellt die zwei benötigten Stacks und füllt stackOrigin mit Karten. Die Karten haben einen zufälligen Wert zwischen 0 und 13
     * Zufallswert: Z.b.: Math.random() -> Double zwischen 0 und 1
     */
    public void startProgram() {
       stackKeep = new Stack<>();
       stackOrigin = new Stack<>();
        for (int i = 0; i < 13; i++) {
            stackOrigin.push(new Card((int)(Math.random()*13)));
        }
    }

    /**
     * Wert der obersten Karte von stackOrigin wird ermittelt. Falls es keine oberste Karte gibt wird -1 zurückgegeben.
     * @return Wert der obersten Karte oder -1
     */
    public int showNextCard() {
       if(stackOrigin.top() != null){
           return stackOrigin.top().getWert();
       }
        return -1;
    }

    /**
     * Falls stackOrigin nicht leer ist, wird die oberste Karte von stackOrigin auf stackKeep gelegt.
     * @return true, falls eine Karte auf stackKeep gelegt wird, sonst false.
     */
    public boolean keep() {
        if(!stackOrigin.isEmpty()) {
            stackKeep.push(stackOrigin.top());
            stackOrigin.pop();
        }
        return false;
    }

    /**
     * Falls stackOrigin nicht leer ist, wird die oberste Karte von stackOrigin entfernt.
     * @return true, falls eine Karte auf stackOrigin entfernt wird, sonst false.
     */
    public boolean throwCard() {
            if(!stackOrigin.isEmpty()){
                stackOrigin.pop();
                return true;
            }
        return false;
    }

    /**
     * Zählt die Karten von stackKeep. Zunächst wird geprüft, ob stackKeep den Regeln entspricht (Nur Karten in aufsteigender Reihenfolge abgelegt).
     * Zählt anschließend die abgelegten Karten, falls stackKeep regelkonform ist.
     * @return Die Anzahl der abgelegten Karten, falls stackKeep regelkonform ist, sonst -1.
     */
    public int inspect() {
        if(keepCorrect()){ //in keepCorrect wurde der Stapel umgedreht
            //Anzahl der Karten
            int kartenCount = 0;
            Stack<Card> HELP = new Stack<>();
            //wir pushen alle karten in HELP und können diesen Prozess zählen (jetzt ist der Stapel in richtiger reihenfolge)
            while(!stackKeep.isEmpty()){
                HELP.push(stackKeep.top());
                stackKeep.pop();
                kartenCount++;
            }

           stackKeep = HELP;
            return kartenCount;
        }
        return -1;
    }

    /**
     * Prüft, ob stackKeep regelkonform ist. Dh. die Karten liegen (von oben nach unten) in absteigender Reihenfolge.
     * Tipp: Nutze einen Hilfsstack
     * @return true, falls regelkonform. Sonst false.
     */
    public boolean keepCorrect() {
        Stack<Card> help = new Stack<>();
        while (!stackKeep.isEmpty()) {
            if (!help.isEmpty()) {
                if (stackKeep.top().getWert() < help.top().getWert()) {
                    help.push(stackKeep.top());
                    stackKeep.pop();
                } else {
                    return false;
                }
            } else {
                help.push(stackKeep.top());
                stackKeep.pop();
            }
        }
        stackKeep = help;
        return true;
    }

    public boolean cardStackEmpty() {
        return stackOrigin.isEmpty();
    }

}
