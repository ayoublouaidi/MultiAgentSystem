package org.sma;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class ClientAgent extends Agent {
    protected void setup() {
        System.out.println("Agent Client pr�t � effectuer une r�servation.");

        addBehaviour(new OneShotBehaviour(this) {
            public void action() {
                // Envoyer une demande de r�servation � l'agent Agence
                ACLMessage request = new ACLMessage(ACLMessage.REQUEST);
                request.addReceiver(getAID());
                request.setContent("DemandeR�servation");
                send(request);
            }
        });

        addBehaviour(new CyclicBehaviour(this) {
            public void action() {
                ACLMessage response = receive();
                if (response != null) {
                    // Afficher le r�sultat de la r�servation
                    System.out.println("R�ponse de l'Agence : " + response.getContent());
                }
            }
        });
    }
}

