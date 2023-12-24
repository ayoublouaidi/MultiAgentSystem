package org.sma;

import jade.core.AgentContainer;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;

public class Main {
    public static void main(String[] args) {

        jade.core.Runtime rt = jade.core.Runtime.instance();
        Profile p = new ProfileImpl();
        AgentContainer container = (AgentContainer) rt.createMainContainer(p);
    
        try {
            // Créer une instance de l'agent Agence
            AgentController agenceController = ((ContainerController) container).createNewAgent("Agence", AgenceAgent.class.getName(), null);
            agenceController.start();

            // Créer une instance de l'agent Client
            AgentController clientController = ((ContainerController) container).createNewAgent("Client", ClientAgent.class.getName(), null);
            clientController.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
