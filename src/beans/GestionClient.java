package beans;

import java.io.*;
import java.net.Socket;

public class GestionClient implements Runnable {
    private final Socket socket;
    private final BufferedReader reader;
    private PrintWriter writer;
    private Employee employee;

    public GestionClient(Socket socket, Agenda agenda) throws IOException {
        this.socket = socket;
        reader = new BufferedReader(new
                InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        employee = new Employee();
        employee.setAgenda(agenda);
    }

    @Override
    public void run() {
        String dataReceived = "";
        while (true) {
            try {
                 dataReceived = reader.readLine();
                if (!dataReceived.equals("")){
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        writer.println("---------------------------------\n");
        writer.println("Bienvenue employée : " + employee + "\n");
        writer.println("Voici votre Agenda :\n" + employee.getAgenda());
        writer.println("---------------------------------\n");

        writer.flush();
        writer.close();

    }
}