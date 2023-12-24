package org.sma;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class AgenceAgent extends Agent {
    private int billetsDisponibles = 10;
    protected void setup() {
        System.out.println("Agent Agence pr�t � g�rer les r�servations.");

        addBehaviour(new CyclicBehaviour(this) {
            public void action() {
                ACLMessage message = receive();
                if (message != null) {
                    if (message.getContent().equals("DemandeR�servation")) {
                        if (billetsDisponibles > 0) {
                            // Envoyer une r�ponse positive au Client
                            ACLMessage response = new ACLMessage(ACLMessage.INFORM);
                            response.addReceiver(message.getSender());
                            response.setContent("R�servationAccept�e");
                            send(response);

                            // Mettre � jour le nombre de billets disponibles
                            billetsDisponibles--;
                        } else {
                            // Envoyer une r�ponse n�gative au Client
                            ACLMessage response = new ACLMessage(ACLMessage.INFORM);
                            response.addReceiver(message.getSender());
                            response.setContent("PlusDeBillets");
                            send(response);
                        }
                    }
                }
            }
        });

        addBehaviour(new OneShotBehaviour(this) {
            public void action() {
                // Comportement pour afficher le nombre actuel de billets disponibles
                System.out.println("Nombre de billets disponibles : " + billetsDisponibles);
            }
        });
    }
}