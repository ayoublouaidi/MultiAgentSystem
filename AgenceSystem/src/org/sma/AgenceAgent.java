package org.sma;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class AgenceAgent extends Agent {
    private int billetsDisponibles = 10;
    protected void setup() {
        System.out.println("Agent Agence prêt à gérer les réservations.");

        addBehaviour(new CyclicBehaviour(this) {
            public void action() {
                ACLMessage message = receive();
                if (message != null) {
                    if (message.getContent().equals("DemandeRéservation")) {
                        if (billetsDisponibles > 0) {
                            // Envoyer une réponse positive au Client
                            ACLMessage response = new ACLMessage(ACLMessage.INFORM);
                            response.addReceiver(message.getSender());
                            response.setContent("RéservationAcceptée");
                            send(response);

                            // Mettre à jour le nombre de billets disponibles
                            billetsDisponibles--;
                        } else {
                            // Envoyer une réponse négative au Client
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