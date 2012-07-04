/*
 * Main.java
 *
 * Created on Sep 23, 2009, 11:22:07 PM
 */
package jsniffer;

import jpcap.*;
import jpcap.packet.Packet;

/**
 *
 * @author Sergio Ceron Figueroa
 */

class Main implements PacketReceiver {

    public void receivePacket(Packet packet) {
        System.out.println(packet);
    }

    public static void main(String[] args) throws Exception {
        NetworkInterface[] devices = JpcapCaptor.getDeviceList();

        int device = 0;
        String filter = "";

        if( devices.length < 1 ){
            System.err.println("No se han detectado tarjetas de red compatibles.");
            System.err.println("Asegurese de tener al menos una conectada.");
            System.err.println("\nTambien puede que no se haya ejecutado el programa con suficientes privilegios.");
            return;
        }
        System.out.println("Seleccione una tarjeta de red:");

        for (int i = 0; i < devices.length; i++) {
            System.out.println(i + " :" + devices[i].name + "(" + devices[i].description + ")");
            for (NetworkInterfaceAddress a : devices[i].addresses) {
                System.out.print("    IP:" + a.address);
            }
            System.out.println();
        }

        

        JpcapCaptor jpcap = JpcapCaptor.openDevice(devices[device], 2000, false, 20);
        jpcap.loopPacket(-1, new Main());
    }
}
