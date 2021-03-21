package example.hello;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server implements Hello {

    private static final String HELLO_MESSAGE = "Hello World";
    private static final int SERVER_PORT = 7777;

    @Override
    public String sayHello() throws RemoteException {
        return HELLO_MESSAGE;
    }

    public static void main(String[] args) {

        try {
            Server server = new Server();
            Hello stub = (Hello) UnicastRemoteObject.exportObject(server, SERVER_PORT);

            Registry registry = LocateRegistry.getRegistry();
            registry.bind("Hello", stub);

        } catch (Exception e) {
            System.err.println("Server exception " + e.toString());
            e.printStackTrace();
        }
    }
}
