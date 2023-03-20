package mp9.uf3.tcp.jocObj;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;

public class ClientAdivinaMulticast {

    private boolean continueRunning = true;
    private MulticastSocket socket;
    private InetAddress multicastIP;
    private int port;
    NetworkInterface netIf;
    InetSocketAddress group;


    public ClientAdivinaMulticast(int portValue, String strIp) throws IOException {
        multicastIP = InetAddress.getByName(strIp);
        port = portValue;
        socket = new MulticastSocket(port);
        //netIf = NetworkInterface.getByName("enp1s0");
        netIf = socket.getNetworkInterface();
        group = new InetSocketAddress(strIp,portValue);
    }

    public void runClient() throws IOException{
        DatagramPacket packet;
        byte [] receivedData = new byte[1024];

        socket.joinGroup(group,netIf);
        System.out.printf("Conectado a :",group.getAddress(),group.getPort());

        while(continueRunning){
            packet = new DatagramPacket(receivedData, receivedData.length);
            socket.setSoTimeout(5000);
            try{
                socket.receive(packet);
                continueRunning = getData(packet.getData());
            }catch(SocketTimeoutException e){
                System.out.println("Conexión perdida");
                continueRunning = true;
            }
            catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        socket.leaveGroup(group,netIf);
        socket.close();
    }
    protected  boolean getData(byte[] data) throws IOException, ClassNotFoundException {
        boolean ret = false;

        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Tauler tauler = (Tauler) ois.readObject();
        System.out.println(tauler.toString());

        if(tauler.acabats == tauler.getNumPlayers()){
            System.out.println("Ha acabo la partida!");
            ret = true;}

        return ret;
    }
}
