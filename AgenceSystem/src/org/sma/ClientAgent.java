package org.sma;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class ClientAgent extends Agent {
    protected void setup() {
        System.out.println("Agent Client prêt à effectuer une réservation.");

        addBehaviour(new OneShotBehaviour(this) {
            public void action() {
                // Envoyer une demande de réservation à l'agent Agence
                ACLMessage request = new ACLMessage(ACLMessage.REQUEST);
                request.addReceiver(getAID());
                request.setContent("DemandeRéservation");
                send(request);
            }
        });

        addBehaviour(new CyclicBehaviour(this) {
            public void action() {
                ACLMessage response = receive();
                if (response != null) {
                    // Afficher le résultat de la réservation
                    System.out.println("Réponse de l'Agence : " + response.getContent());
                }
            }
        });
    }
}

